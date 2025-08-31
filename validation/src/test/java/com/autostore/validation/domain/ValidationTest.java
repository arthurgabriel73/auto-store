package com.autostore.validation.domain;


import com.autostore.validation.domain.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ValidationTest {

    @Test
    void testShouldCreateValidationSuccessfully() {
        // Arrange
        var now = java.time.LocalDateTime.now();

        // Act
        Validation validation = Validation.builder()
                .id(1L)
                .orderId("order123")
                .transactionId("txn123")
                .createdAt(now)
                .updatedAt(now)
                .success(true)
                .build();

        // Assert
        assertEquals(1L, validation.getId());
        assertEquals("order123", validation.getOrderId());
        assertEquals("txn123", validation.getTransactionId());
        assertEquals(now, validation.getCreatedAt());
        assertEquals(now, validation.getUpdatedAt());
        assertTrue(validation.isSuccess());
    }

    @Test
    void testShouldCreateValidationWithNullId() {
        // Arrange
        var now = java.time.LocalDateTime.now();

        // Act
        Validation validation = Validation.builder()
                .orderId("order123")
                .transactionId("txn123")
                .createdAt(now)
                .updatedAt(now)
                .success(true)
                .build();

        // Assert
        assertNull(validation.getId());
        assertEquals("order123", validation.getOrderId());
        assertEquals("txn123", validation.getTransactionId());
        assertEquals(now, validation.getCreatedAt());
        assertEquals(now, validation.getUpdatedAt());
        assertTrue(validation.isSuccess());
    }

    @Test
    void testShouldThrowExceptionWhenMarkingFailedTwice() {
        // Arrange
        var now = java.time.LocalDateTime.now();
        Validation validation = Validation.builder()
                .id(1L)
                .orderId("order123")
                .transactionId("txn123")
                .createdAt(now)
                .updatedAt(now)
                .success(true)
                .build();

        validation.markFailed();

        // Act & Assert
        assertThrows(BusinessException.class, validation::markFailed);
    }

}
