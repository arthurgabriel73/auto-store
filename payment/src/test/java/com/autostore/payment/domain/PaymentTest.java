package com.autostore.payment.domain;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PaymentTest {

    @Test
    void testShouldCreatePaymentSuccessfully() {
        // Arrange
        var now = LocalDateTime.now();

        // Act
        Payment payment = Payment.builder()
                .id(1L)
                .orderId("order123")
                .transactionId("txn123")
                .totalItems(2)
                .totalAmount(1_250_700.50)
                .status(PaymentStatus.PENDING)
                .createdAt(now)
                .updatedAt(now)
                .build();

        // Assert
        assertEquals(1L, payment.getId());
        assertEquals("order123", payment.getOrderId());
        assertEquals("txn123", payment.getTransactionId());
        assertEquals(2, payment.getTotalItems());
        assertEquals(1_250_700.50, payment.getTotalAmount());
        assertEquals(PaymentStatus.PENDING, payment.getStatus());
        assertEquals(now, payment.getCreatedAt());
        assertEquals(now, payment.getUpdatedAt());
    }

    @Test
    void testShouldCreatePaymentSuccessfullyWithNullId() {
        // Arrange
        var now = LocalDateTime.now();

        // Act
        Payment payment = Payment.builder()
                .orderId("order123")
                .transactionId("txn123")
                .totalItems(2)
                .totalAmount(1_250_700.50)
                .status(PaymentStatus.PENDING)
                .createdAt(now)
                .updatedAt(now)
                .build();

        // Assert
        assertEquals("order123", payment.getOrderId());
        assertEquals("txn123", payment.getTransactionId());
        assertEquals(2, payment.getTotalItems());
        assertEquals(1_250_700.50, payment.getTotalAmount());
        assertEquals(PaymentStatus.PENDING, payment.getStatus());
        assertEquals(now, payment.getCreatedAt());
        assertEquals(now, payment.getUpdatedAt());
    }

}
