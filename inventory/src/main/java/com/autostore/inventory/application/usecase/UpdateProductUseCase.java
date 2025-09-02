package com.autostore.inventory.application.usecase;


import com.autostore.inventory.application.exception.ProductNotFoundException;
import com.autostore.inventory.application.port.driven.ProductRepository;
import com.autostore.inventory.application.port.driver.UpdateProductDriverPort;
import com.autostore.inventory.application.port.driver.model.command.UpdateProductCommand;
import com.autostore.inventory.domain.Product;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UpdateProductUseCase implements UpdateProductDriverPort {

    private final ProductRepository productRepository;

    @Override
    public void execute(UpdateProductCommand command) {
        var existingProduct = getProductOfFail(command.code());
        var productToUpdate = Product.builder()
                .id(existingProduct.getId())
                .code(existingProduct.getCode())
                .unitValue(command.unitValue())
                .category(command.category())
                .details(command.details())
                .build();
        updateProduct(productToUpdate);
    }

    private Product getProductOfFail(String code) {
        return productRepository.findByCode(code)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with code: " + code));
    }

    private void updateProduct(Product product) {
        productRepository.save(product);
    }

}
