package com.autostore.order.application;


import com.autostore.order.application.port.driver.model.command.CreateOrderCommand;
import com.autostore.order.application.port.event.Topic;
import com.autostore.order.application.usecase.CreateOrderUseCase;
import com.autostore.order.domain.OrderProducts;
import com.autostore.order.domain.Product;
import com.autostore.order.infrastructure.adapter.driven.event.FakeEventProducer;
import com.autostore.order.infrastructure.adapter.driven.persistence.InMemoryOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class CreateOrderUseCaseTest {

    private static final CreateOrderCommand command = new CreateOrderCommand(
            List.of(
                    new OrderProducts(new Product("product-1", 1_000_000.50), 1),
                    new OrderProducts(new Product("product-2", 7_000.00), 1)
            ),
            "customer-id"
    );

    private InMemoryOrderRepository orderRepository;
    private FakeEventProducer eventProducer;
    private CreateOrderUseCase sut;

    @BeforeEach
    void setUp() {
        orderRepository = new InMemoryOrderRepository();
        eventProducer = new FakeEventProducer();
        sut = new CreateOrderUseCase(orderRepository, eventProducer);
    }

    @Test
    void testShouldCreateOrderSuccessfully() {
        // Arrange & Act
        var output = sut.execute(command);

        // Assert
        var events = eventProducer.getEvents();
        assertNotNull(output);
        assertNotNull(output.order());
        assertNotNull(output.order().getId());
        assertEquals(command.customerId(), output.order().getCustomer().getCustomerId());
        assertEquals(2, output.order().getProducts().size());
        assertEquals(1_007_000.50, output.order().getTotalAmount());
        assertEquals(2, output.order().getTotalItems());
        assertEquals(1, events.size());
        assertNotNull(events.get(Topic.ORDER_SERVICE_ORDER_CREATED_V1.getTopic()));
    }

}
