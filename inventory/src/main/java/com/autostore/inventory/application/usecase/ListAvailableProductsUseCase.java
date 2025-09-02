package com.autostore.inventory.application.usecase;


import com.autostore.inventory.application.port.driven.ProductRepository;
import com.autostore.inventory.application.port.driver.ListAvailableProductsDriverPort;
import com.autostore.inventory.application.port.driver.model.query.ListAvailableProductsQueryOutput;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ListAvailableProductsUseCase implements ListAvailableProductsDriverPort {

    private final ProductRepository productRepository;

    @Override
    public ListAvailableProductsQueryOutput query() {
        var products = productRepository.findAllAvailableProducts();
        return new ListAvailableProductsQueryOutput(products);
    }

}
