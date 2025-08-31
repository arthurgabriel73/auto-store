package com.autostore.validation.application;


import com.autostore.validation.application.exception.ProductAlreadyRegisteredException;
import com.autostore.validation.application.port.driver.model.command.RegisterValidationProductCommand;
import com.autostore.validation.application.usecase.RegisterValidationProductUseCase;
import com.autostore.validation.domain.ValidationProduct;
import com.autostore.validation.infrastructure.adapter.driven.persistence.InMemoryValidationProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class RegisterValidationProductUseCaseTest {

    private InMemoryValidationProductRepository inMemoryValidationProductRepository;
    private RegisterValidationProductUseCase sut;

    @BeforeEach
    void setUp() {
        inMemoryValidationProductRepository = new InMemoryValidationProductRepository();
        sut = new RegisterValidationProductUseCase(inMemoryValidationProductRepository);
    }

    @Test
    void testShouldRegisterProductSuccessfully() {
        // Arrange
        var command = new RegisterValidationProductCommand("VALID_CODE_123");

        // Act
        sut.execute(command);

        // Assert
        assert inMemoryValidationProductRepository.existsByCode("VALID_CODE_123");
    }

    @Test
    void testShouldThrowWhenRegisteringDuplicateProduct() {
        // Arrange
        var productCode = "DUPLICATE_CODE_456";
        inMemoryValidationProductRepository.save(ValidationProduct.builder().code(productCode).build());

        // Act & Assert
        assertThrows(ProductAlreadyRegisteredException.class, () -> {
            var command = new RegisterValidationProductCommand(productCode);
            sut.execute(command);
        });
    }

}
