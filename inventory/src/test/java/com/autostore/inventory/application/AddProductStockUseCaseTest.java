package com.autostore.inventory.application;


import com.autostore.inventory.application.exception.ApplicationException;
import com.autostore.inventory.application.port.driver.model.command.AddProductStockCommand;
import com.autostore.inventory.application.usecase.AddProductStockUseCase;
import com.autostore.inventory.domain.Inventory;
import com.autostore.inventory.domain.Product;
import com.autostore.inventory.domain.ProductCategory;
import com.autostore.inventory.infrastructure.adapter.driven.persistence.InMemoryInventoryRepository;
import com.autostore.inventory.infrastructure.adapter.driven.persistence.InMemoryProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AddProductStockUseCaseTest {

    private InMemoryProductRepository productRepository;
    private InMemoryInventoryRepository inventoryRepository;
    private AddProductStockUseCase sut;

    @BeforeEach
    void setUp() {
        productRepository = new InMemoryProductRepository();
        inventoryRepository = new InMemoryInventoryRepository();
        sut = new AddProductStockUseCase(productRepository, inventoryRepository);
    }

    @Test
    void testShouldAddProductStockSuccessfully() {
        // Arrange
        var oldQuantity = 5;
        var quantityToAdd = 10;
        var expectedQuantity = oldQuantity + quantityToAdd;
        var productCode = "GFG423456E";
        productRepository.save(
                Product.builder()
                        .id(null)
                        .code(productCode)
                        .unitValue(50_000.00)
                        .category(ProductCategory.VEHICLE)
                        .build()
        );
        inventoryRepository.save(Inventory.builder()
                .id(null)
                .productCode(productCode)
                .availableQuantity(5)
                .build());
        var command = new AddProductStockCommand(productCode, 10);

        // Act
        sut.execute(command);

        // Assert
        var inventory = inventoryRepository.findByProductCode(productCode);
        assertTrue(inventory.isPresent());
        assertEquals(expectedQuantity, inventory.get().getAvailableQuantity());
    }

    @Test
    void testShouldThrowExceptionWhenProductDoesNotExist() {
        // Arrange
        var command = new AddProductStockCommand("NON_EXISTENT_CODE", 10);

        // Act & Assert
        assertThrows(ApplicationException.class, () -> sut.execute(command));
    }

}
