package com.autostore.inventory.application;


import com.autostore.inventory.application.port.driver.model.command.UpdateInventoryCommand;
import com.autostore.inventory.application.usecase.UpdateInventoryUseCase;
import com.autostore.inventory.domain.Activity;
import com.autostore.inventory.domain.Inventory;
import com.autostore.inventory.domain.Product;
import com.autostore.inventory.domain.ProductCategory;
import com.autostore.inventory.domain.event.Order;
import com.autostore.inventory.domain.event.OrderEvent;
import com.autostore.inventory.domain.event.OrderProducts;
import com.autostore.inventory.domain.event.Topic;
import com.autostore.inventory.infrastructure.adapter.driven.event.FakeEventProducer;
import com.autostore.inventory.infrastructure.adapter.driven.persistence.InMemoryActivityRepository;
import com.autostore.inventory.infrastructure.adapter.driven.persistence.InMemoryInventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class UpdateInventoryUseCaseTest {

    private static final UpdateInventoryCommand command = new UpdateInventoryCommand(
            OrderEvent
                    .builder()
                    .id("event-id")
                    .createdAt(LocalDateTime.now())
                    .payload(Order
                            .builder()
                            .id("order-id")
                            .createdAt(LocalDateTime.of(2024, 1, 1, 0, 0))
                            .transactionId("transaction-id")
                            .totalAmount(1200_000.99)
                            .totalItems(2)
                            .products(
                                    List.of(
                                            new OrderProducts(Product.builder()
                                                    .id(1L)
                                                    .code("product-1")
                                                    .unitValue(1_000_000.50)
                                                    .category(ProductCategory.VEHICLE)
                                                    .details(
                                                            Map.of(
                                                                    "brand", "Toyota",
                                                                    "model", "Corolla",
                                                                    "year", "2024"

                                                            )
                                                    )
                                                    .build(), 1),
                                            new OrderProducts(Product.builder()
                                                    .id(2L)
                                                    .code("product-2")
                                                    .unitValue(200_000.49)
                                                    .category(ProductCategory.PARTS)
                                                    .details(
                                                            Map.of(
                                                                    "specification", "spoiler for my palio",
                                                                    "model", "2026",
                                                                    "year", "2025"

                                                            )
                                                    )
                                                    .build(), 1)

                                    )
                            )
                            .build())
                    .build()
    );

    private InMemoryActivityRepository activityRepository;
    private InMemoryInventoryRepository inventoryRepository;
    private FakeEventProducer eventProducer;
    private UpdateInventoryUseCase sut;

    @BeforeEach
    void setUp() {
        activityRepository = new InMemoryActivityRepository();
        inventoryRepository = new InMemoryInventoryRepository();
        eventProducer = new FakeEventProducer();
        sut = new UpdateInventoryUseCase(activityRepository, inventoryRepository, eventProducer);
    }

    @Test
    void testShouldUpdateInventorySuccessfully() {
        // Arrange
        inventoryRepository.save(Inventory.builder().id(null).productCode("product-1").availableQuantity(1).build());
        inventoryRepository.save(Inventory.builder().id(null).productCode("product-2").availableQuantity(100).build());

        // Act
        sut.execute(command);

        // Assert
        var updatedI1 = inventoryRepository.findByProductCode("product-1").orElseThrow();
        var updatedI2 = inventoryRepository.findByProductCode("product-2").orElseThrow();
        var activities = activityRepository.findByOrderIdAndTransactionId("order-id", "transaction-id");
        var events = eventProducer.getEvents();

        assertEquals(0, updatedI1.getAvailableQuantity());
        assertEquals(99, updatedI2.getAvailableQuantity());
        assertEquals(2, activities.size());
        assertEquals(1, events.size());
        assertEquals(command.event(), events.get(Topic.INVENTORY_SERVICE_INVENTORY_UPDATED_V1.getTopic()));
    }

    @Test
    void testShouldThrowDuplicatedActivityException() {
        // Arrange
        var now = LocalDateTime.now();
        Inventory i1 =
                inventoryRepository.save(Inventory.builder().id(null).productCode("product-1").availableQuantity(1).build());
        Inventory i2 =
                inventoryRepository.save(Inventory.builder().id(null).productCode("product-2").availableQuantity(100).build());
        activityRepository.save(Activity.builder()
                .id(null)
                .inventory(i1)
                .orderId("order-id")
                .transactionId("transaction-id")
                .orderQuantity(1)
                .oldQuantity(1)
                .newQuantity(0)
                .createdAt(now)
                .updatedAt(now)
                .build());
        activityRepository.save(Activity.builder()
                .id(null)
                .inventory(i2)
                .orderId("order-id")
                .transactionId("transaction-id")
                .orderQuantity(1)
                .oldQuantity(100)
                .newQuantity(99)
                .createdAt(now)
                .updatedAt(now)
                .build());
        sut.execute(command);

        // Act & Assert
        var events = eventProducer.getEvents();
        assertEquals(1, events.size());
        assertEquals(command.event(), events.get(Topic.INVENTORY_SERVICE_INVENTORY_FAILED_V1.getTopic()));
    }

    @Test
    void testShouldRollbackInventorySuccessfully() {
        // Arrange
        inventoryRepository.save(Inventory.builder().id(null).productCode("product-1").availableQuantity(1).build());
        inventoryRepository.save(Inventory.builder().id(null).productCode("product-2").availableQuantity(100).build());

        // Act
        sut.execute(command);
        var updatedI1 = inventoryRepository.findByProductCode("product-1").orElseThrow();
        var updatedI2 = inventoryRepository.findByProductCode("product-2").orElseThrow();
        var activities = activityRepository.findByOrderIdAndTransactionId("order-id", "transaction-id");
        var events = eventProducer.getEvents();

        // Assert
        assertEquals(0, updatedI1.getAvailableQuantity());
        assertEquals(99, updatedI2.getAvailableQuantity());
        assertEquals(2, activities.size());
        assertEquals(1, events.size());
        assertEquals(command.event(), events.get(Topic.INVENTORY_SERVICE_INVENTORY_UPDATED_V1.getTopic()));

        // Act - Rollback
        sut.rollback(command.event());
        var rolledBackI1 = inventoryRepository.findByProductCode("product-1").orElseThrow();
        var rolledBackI2 = inventoryRepository.findByProductCode("product-2").orElseThrow();
        var rolledBackActivities = activityRepository.findByOrderIdAndTransactionId("order-id", "transaction-id");
        var totalEvents = eventProducer.getEvents();

        // Assert - Rollback
        assertEquals(1, rolledBackI1.getAvailableQuantity());
        assertEquals(100, rolledBackI2.getAvailableQuantity());
        assertEquals(2, rolledBackActivities.size());
        assertEquals(2, totalEvents.size());
        assertEquals(command.event(), totalEvents.get(Topic.INVENTORY_SERVICE_INVENTORY_ROLLBACK_SUCCESS_V1.getTopic()));
        assertNotNull(totalEvents.get(Topic.INVENTORY_SERVICE_INVENTORY_UPDATED_V1.getTopic()));
        assertNotNull(totalEvents.get(Topic.INVENTORY_SERVICE_INVENTORY_ROLLBACK_SUCCESS_V1.getTopic()));
    }

}
