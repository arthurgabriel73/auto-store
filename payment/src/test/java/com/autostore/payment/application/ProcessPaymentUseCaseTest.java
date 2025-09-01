package com.autostore.payment.application;


import com.autostore.payment.application.port.driver.model.command.ProcessPaymentCommand;
import com.autostore.payment.application.port.event.*;
import com.autostore.payment.application.usecase.ProcessPaymentUseCase;
import com.autostore.payment.domain.Payment;
import com.autostore.payment.domain.PaymentStatus;
import com.autostore.payment.infrastructure.adapter.driven.event.FakeEventProducer;
import com.autostore.payment.infrastructure.adapter.driven.persistence.InMemoryPaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ProcessPaymentUseCaseTest {

    private static final ProcessPaymentCommand command = new ProcessPaymentCommand(
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

    private InMemoryPaymentRepository paymentRepository;
    private FakeEventProducer eventProducer;
    private ProcessPaymentUseCase sut;

    @BeforeEach
    void setUp() {
        paymentRepository = new InMemoryPaymentRepository();
        eventProducer = new FakeEventProducer();
        sut = new ProcessPaymentUseCase(paymentRepository, eventProducer);
    }

    @Test
    void testShouldProcessPaymentSuccessfully() {
        // Arrange & Act
        sut.execute(command);

        // Assert
        var events = eventProducer.getEvents();
        assertEquals(1, events.size());
        assertEquals(command.event(), events.get(Topic.PAYMENT_SERVICE_PAYMENT_PROCESSED_V1.getTopic()));
    }

    @Test
    void testShouldThrowWhenTryingToProcessDuplicatedPendingPayment() {
        // Arrange
        paymentRepository.save(Payment.builder()
                .id(null)
                .orderId("order-id")
                .transactionId("transaction-id")
                .totalAmount(1200_000.99)
                .totalItems(2)
                .status(PaymentStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build());

        // Act
        sut.execute(command);

        // Assert
        var events = eventProducer.getEvents();

        assertEquals(1, events.size());
        assertEquals(command.event(), events.get(Topic.PAYMENT_SERVICE_PAYMENT_FAILED_V1.getTopic()));
    }

    @Test
    void testShouldRefundPaymentSuccessfully() {
        // Arrange
        sut.execute(command);

        // Act
        sut.rollback(command.event());

        // Assert
        var events = eventProducer.getEvents();

        assertEquals(2, events.size());
        assertNotNull(events.get(Topic.PAYMENT_SERVICE_PAYMENT_REFUND_SUCCESS_V1.getTopic()));
        assertEquals(command.event(), events.get(Topic.PAYMENT_SERVICE_PAYMENT_REFUND_SUCCESS_V1.getTopic()));
    }

}
