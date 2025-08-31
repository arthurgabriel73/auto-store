package com.autostore.inventory.domain;


import com.autostore.inventory.domain.exception.InventoryOutOfStockException;
import com.autostore.inventory.domain.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class InventoryTest {

    @Test
    void testShouldCreateInventorySuccessfully() {
        // Arrange & Act
        Inventory inventory = Inventory.builder()
                .id(1L)
                .productCode("PROD-001")
                .availableQuantity(100)
                .build();

        // Assert
        assertEquals(1L, inventory.getId());
        assertEquals("PROD-001", inventory.getProductCode());
        assertEquals(100, inventory.getAvailableQuantity());
    }

    @Test
    void testShouldCreateInventoryWithNullId() {
        // Arrange & Act
        Inventory inventory = Inventory.builder()
                .productCode("PROD-002")
                .availableQuantity(50)
                .build();

        // Assert
        assertEquals("PROD-002", inventory.getProductCode());
        assertEquals(50, inventory.getAvailableQuantity());
    }

    @Test
    void testShouldIncreaseAvailableQuantity() {
        // Arrange
        Inventory inventory = Inventory.builder()
                .id(1L)
                .productCode("PROD-003")
                .availableQuantity(200)
                .build();

        // Act
        inventory.increaseQuantityBy(150);

        // Assert
        assertEquals(350, inventory.getAvailableQuantity());
    }

    @Test
    void testShouldDecreaseAvailableQuantity() {
        // Arrange
        Inventory inventory = Inventory.builder()
                .id(1L)
                .productCode("PROD-004")
                .availableQuantity(300)
                .build();

        // Act
        inventory.decreaseQuantityBy(100);

        // Assert
        assertEquals(200, inventory.getAvailableQuantity());
    }

    @Test
    void testShouldNotDecreaseAvailableQuantityBelowZero() {
        // Arrange
        Inventory inventory = Inventory.builder()
                .id(1L)
                .productCode("PROD-005")
                .availableQuantity(50)
                .build();

        // Act & Assert
        assertThrows(InventoryOutOfStockException.class, () -> inventory.decreaseQuantityBy(100));
    }

    @Test
    void testShouldNotIncreaseAvailableQuantityByNegativeAmount() {
        // Arrange
        Inventory inventory = Inventory.builder()
                .id(1L)
                .productCode("PROD-006")
                .availableQuantity(50)
                .build();

        // Act & Assert
        assertThrows(ValidationException.class, () -> inventory.increaseQuantityBy(-10));
    }

    @Test
    void testShouldNotDecreaseAvailableQuantityByNegativeAmount() {
        // Arrange
        Inventory inventory = Inventory.builder()
                .id(1L)
                .productCode("PROD-007")
                .availableQuantity(50)
                .build();

        // Act & Assert
        assertThrows(ValidationException.class, () -> inventory.decreaseQuantityBy(-10));
    }

}
