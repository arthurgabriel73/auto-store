package com.autostore.inventory.application;


import com.autostore.inventory.application.exception.ProductCodeAlreadyRegisteredException;
import com.autostore.inventory.application.port.driver.model.command.RegisterProductCommand;
import com.autostore.inventory.application.usecase.RegisterProductUseCase;
import com.autostore.inventory.domain.ProductCategory;
import com.autostore.inventory.infrastructure.adapter.driven.persistence.InMemoryInventoryRepository;
import com.autostore.inventory.infrastructure.adapter.driven.persistence.InMemoryProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class RegisterProductUseCaseTest {

    private static final RegisterProductCommand command = new RegisterProductCommand(
            "HD8883HD",
            100_000.00,
            ProductCategory.VEHICLE,
            Map.of(
                    "brand", "Toyota",
                    "model", "Corolla",
                    "year", 2020,
                    "color", "White",
                    "mileage", 15000,
                    "features", new String[]{"Air Conditioning", "Navigation System", "Bluetooth"}
            )
    );

    private InMemoryProductRepository productRepository;
    private InMemoryInventoryRepository inventoryRepository;
    private RegisterProductUseCase sut;

    @BeforeEach
    void setUp() {
        productRepository = new InMemoryProductRepository();
        inventoryRepository = new InMemoryInventoryRepository();
        sut = new RegisterProductUseCase(productRepository, inventoryRepository);
    }

    @Test
    void testShouldRegisterProductSuccessfully() {
        // Act
        var output = sut.execute(command);

        // Assert
        assertNotNull(output);
        assertNotNull(output.getProductId());
        assertEquals(command.code(), output.getProductCode());
        assertEquals(command.unitValue(), output.getUnitValue());
        assertEquals(command.category().name(), output.getCategory());
        var productInventory = inventoryRepository.findByProductCode(command.code());
        assertTrue(productInventory.isPresent());
        assertEquals(0, productInventory.get().getAvailableQuantity());
    }

    @Test
    void testShouldThrowExceptionWhenRegisteringProductWithExistingCode() {
        // Arrange
        sut.execute(command);

        // Act & Assert
        assertThrows(ProductCodeAlreadyRegisteredException.class, () -> sut.execute(command));
    }

}
