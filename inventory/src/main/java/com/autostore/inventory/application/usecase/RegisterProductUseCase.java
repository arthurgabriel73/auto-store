package com.autostore.inventory.application.usecase;


import com.autostore.inventory.application.exception.ProductCodeAlreadyRegisteredException;
import com.autostore.inventory.application.port.driven.ProductRepository;
import com.autostore.inventory.application.port.driver.RegisterProductDriverPort;
import com.autostore.inventory.application.port.driver.model.command.RegisterProductCommand;
import com.autostore.inventory.application.port.driver.model.command.RegisterProductCommandOutput;
import com.autostore.inventory.domain.Product;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class RegisterProductUseCase implements RegisterProductDriverPort {

    private final ProductRepository productRepository;

    @Override
    public RegisterProductCommandOutput execute(RegisterProductCommand command) {
        Product product = createProductEntityFromCommand(command);
        requireProductNotRegistered(product.getCode());
        return saveProduct(product);
    }

    private Product createProductEntityFromCommand(RegisterProductCommand command) {
        return Product
                .builder()
                .code(command.code())
                .unitValue(command.unitValue())
                .category(command.category())
                .details(command.details())
                .build();
    }

    private void requireProductNotRegistered(String code) {
        if (productRepository.existsByCode(code))
            throw new ProductCodeAlreadyRegisteredException("Product code already registered: " + code);
    }

    private RegisterProductCommandOutput saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return RegisterProductCommandOutput.of(
                savedProduct.getId().toString(),
                savedProduct.getCode(),
                savedProduct.getUnitValue(),
                savedProduct.getCategory().name()
        );
    }

}
