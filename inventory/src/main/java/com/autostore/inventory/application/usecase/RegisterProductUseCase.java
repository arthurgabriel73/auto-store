package com.autostore.inventory.application.usecase;


import com.autostore.inventory.application.exception.ProductCodeAlreadyRegisteredException;
import com.autostore.inventory.application.port.driven.InventoryRepository;
import com.autostore.inventory.application.port.driven.ProductRepository;
import com.autostore.inventory.application.port.driver.RegisterProductDriverPort;
import com.autostore.inventory.application.port.driver.model.command.RegisterProductCommand;
import com.autostore.inventory.application.port.driver.model.command.RegisterProductCommandOutput;
import com.autostore.inventory.domain.Inventory;
import com.autostore.inventory.domain.Product;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class RegisterProductUseCase implements RegisterProductDriverPort {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public RegisterProductCommandOutput execute(RegisterProductCommand command) {
        Product product = createProductEntityFromCommand(command);
        requireProductNotRegistered(product.getCode());
        createNewInventoryForProduct(product);
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

    private void createNewInventoryForProduct(Product product) {
        Inventory newInventory = Inventory.builder()
                .productCode(product.getCode())
                .availableQuantity(0)
                .build();
        inventoryRepository.save(newInventory);
    }

    private RegisterProductCommandOutput saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return RegisterProductCommandOutput.of(
                savedProduct.getId(),
                savedProduct.getCode(),
                savedProduct.getUnitValue(),
                savedProduct.getCategory().name()
        );
    }

}
