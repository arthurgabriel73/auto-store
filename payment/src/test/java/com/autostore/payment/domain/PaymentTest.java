package com.autostore.payment.domain;


import com.autostore.payment.domain.exceptions.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


public class PaymentTest {

    private static Payment generatePaymentWithStatus(Long id, PaymentStatus status) {
        var now = LocalDateTime.now();
        return Payment.builder()
                .id(id)
                .orderId("order123")
                .transactionId("txn123")
                .totalItems(2)
                .totalAmount(1_250_700.50)
                .status(status)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    @Test
    void testShouldCreatePaymentSuccessfully() {
        // Arrange & Act
        Payment payment = generatePaymentWithStatus(1L, PaymentStatus.PENDING);

        // Assert
        assertEquals(1L, payment.getId());
        assertEquals("order123", payment.getOrderId());
        assertEquals("txn123", payment.getTransactionId());
        assertEquals(2, payment.getTotalItems());
        assertEquals(1_250_700.50, payment.getTotalAmount());
        assertEquals(PaymentStatus.PENDING, payment.getStatus());
        assertNotNull(payment.getCreatedAt());
        assertNotNull(payment.getUpdatedAt());
    }

    @Test
    void testShouldCreatePaymentSuccessfullyWithNullId() {
        // Arrange & Act
        Payment payment = generatePaymentWithStatus(null, PaymentStatus.PENDING);

        // Assert
        assertEquals("order123", payment.getOrderId());
        assertEquals("txn123", payment.getTransactionId());
        assertEquals(2, payment.getTotalItems());
        assertEquals(1_250_700.50, payment.getTotalAmount());
        assertEquals(PaymentStatus.PENDING, payment.getStatus());
        assertNotNull(payment.getCreatedAt());
        assertNotNull(payment.getUpdatedAt());
    }

    @Test
    void testShouldConfirmPaymentSuccessfully() {
        // Arrange
        Payment payment = generatePaymentWithStatus(1L, PaymentStatus.PENDING);

        // Act
        payment.confirmPayment();

        // Assert
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
    }

    @Test
    void testShouldCancelSuccessfully() {
        // Arrange
        Payment payment = generatePaymentWithStatus(1L, PaymentStatus.PENDING);

        // Act
        payment.cancelPayment();

        // Assert
        assertEquals(PaymentStatus.CANCELLED, payment.getStatus());
    }

    @Test
    void testShouldRefundSuccessfully() {
        // Arrange
        Payment payment = generatePaymentWithStatus(1L, PaymentStatus.PENDING);
        payment.confirmPayment();

        // Act
        payment.refundPayment();

        // Assert
        assertEquals(PaymentStatus.REFUND, payment.getStatus());
    }


    @ParameterizedTest
    @EnumSource(value = PaymentStatus.class, names = {"SUCCESS", "REFUND", "CANCELLED"})
    void testShouldThrowExceptionWhenConfirmingNonPendingPayment(PaymentStatus status) {
        // Arrange
        Payment payment = generatePaymentWithStatus(1L, status);

        // Act
        BusinessException exception = assertThrows(BusinessException.class, payment::confirmPayment);

        // Assert
        assertEquals("Payment can only be confirmed from PENDING status", exception.getMessage());
    }

    @ParameterizedTest
    @EnumSource(value = PaymentStatus.class, names = {"PENDING", "REFUND", "CANCELLED"})
    void testShouldThrowExceptionWhenRefundingNonConfirmedPayment(PaymentStatus status) {
        // Arrange
        Payment payment = generatePaymentWithStatus(1L, status);

        // Act
        BusinessException exception = assertThrows(BusinessException.class, payment::refundPayment);

        // Assert
        assertEquals("Payment can only be refunded from SUCCESS status, try CANCEL instead", exception.getMessage());
    }

    @ParameterizedTest
    @EnumSource(value = PaymentStatus.class, names = {"SUCCESS", "REFUND", "CANCELLED"})
    void testShouldThrowExceptionWhenCancellingNonPendingPayment(PaymentStatus status) {
        // Arrange
        Payment payment = generatePaymentWithStatus(1L, status);

        // Act
        BusinessException exception = assertThrows(BusinessException.class, payment::cancelPayment);

        // Assert
        assertEquals("Payment can only be cancelled from PENDING status, try REFUND instead", exception.getMessage());
    }

}
