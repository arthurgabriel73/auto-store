package com.autostore.validation.application;


import com.autostore.validation.application.port.driver.model.command.ValidateProductsCommand;
import com.autostore.validation.application.usecase.ValidateProductsUseCase;
import com.autostore.validation.domain.ValidationProduct;
import com.autostore.validation.domain.event.*;
import com.autostore.validation.infrastructure.adapter.driven.event.FakeEventProducer;
import com.autostore.validation.infrastructure.adapter.driven.persistence.InMemoryValidationProductRepository;
import com.autostore.validation.infrastructure.adapter.driven.persistence.InMemoryValidationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ValidateProductsUseCaseTest {

    private static final ValidateProductsCommand command = new ValidateProductsCommand(
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
                                                    .category("vehicle")
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
                                                    .category("parts")
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

    private InMemoryValidationRepository validationRepository;
    private InMemoryValidationProductRepository validationProductRepository;
    private FakeEventProducer eventProducer;
    private ValidateProductsUseCase sut;

    @BeforeEach
    void setUp() {
        validationRepository = new InMemoryValidationRepository();
        validationProductRepository = new InMemoryValidationProductRepository();
        eventProducer = new FakeEventProducer();
        sut = new ValidateProductsUseCase(
                validationRepository,
                validationProductRepository,
                eventProducer
        );
    }

    @Test
    void testShouldValidateProductsSuccessfully() {
        // Arrange
        validationProductRepository.save(ValidationProduct.builder().id(null).code("product-1").build());
        validationProductRepository.save(ValidationProduct.builder().id(null).code("product-2").build());

        // Act
        sut.execute(command);

        // Assert
        var validation = validationRepository.findByOrderIdAndTransactionId("order-id", "transaction-id");
        var events = eventProducer.getEvents();

        assert validation.isPresent();
        assert validation.get().isSuccess();
        assertEquals(1, events.size());
        assertEquals(command.event(), events.get(Topic.VALIDATION_SERVICE_VALIDATION_UPDATED_V1.getTopic()));
    }

    @Test
    void testShouldThrowExceptionWhenOneOfProductsNotExists() {
        // Arrange
        validationProductRepository.save(ValidationProduct.builder().id(null).code("product-1").build());

        // Act
        sut.execute(command);

        // Assert
        var validation = validationRepository.findByOrderIdAndTransactionId("order-id", "transaction-id");
        var events = eventProducer.getEvents();

        assert validation.isEmpty();
        assertEquals(1, events.size());
        assertEquals(command.event(), events.get(Topic.VALIDATION_SERVICE_VALIDATION_FAILED_V1.getTopic()));
    }

}
