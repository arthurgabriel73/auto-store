package com.autostore.validation.application.usecase;


import com.autostore.validation.application.exception.ProductAlreadyRegisteredException;
import com.autostore.validation.application.port.driven.ValidationProductRepository;
import com.autostore.validation.application.port.driver.RegisterValidationProductDriverPort;
import com.autostore.validation.application.port.driver.model.command.RegisterValidationProductCommand;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class RegisterValidationProductUseCase implements RegisterValidationProductDriverPort {

    private final ValidationProductRepository validationProductRepository;

    @Override
    public void execute(RegisterValidationProductCommand command) {
        var code = command.code();
        requireNotExistsByCode(code);
        saveValidationProduct(code);
    }

    private void requireNotExistsByCode(String code) {
        if (validationProductRepository.existsByCode(code))
            throw new ProductAlreadyRegisteredException("Product with code " + code + " already registered.");
    }

    private void saveValidationProduct(String code) {
        var product = com.autostore.validation.domain.ValidationProduct
                .builder()
                .code(code)
                .build();
        validationProductRepository.save(product);
    }

}
