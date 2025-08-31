package com.autostore.inventory.infrastructure.adapter.driven.persistence;


import com.autostore.inventory.application.port.driven.ProductRepository;
import com.autostore.inventory.domain.Product;

import java.util.ArrayList;
import java.util.List;


public class InMemoryProductRepository implements ProductRepository {

    List<Product> products = new ArrayList<>();

    @Override
    public Product save(Product product) {
        Product newProduct = toNewInstance(product);
        products.add(newProduct);
        return toNewInstance(newProduct);
    }

    @Override
    public Boolean existsByCode(String code) {
        return products.stream()
                .anyMatch(product -> product.getCode().equals(code));
    }

    private Product toNewInstance(Product product) {
        return Product
                .builder()
                .id(product.getId() != null ? product.getId() : products.size() + 1)
                .code(product.getCode())
                .unitValue(product.getUnitValue())
                .category(product.getCategory())
                .details(product.getDetails())
                .build();
    }

}
