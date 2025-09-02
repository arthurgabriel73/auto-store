package com.autostore.inventory.infrastructure.adapter.driven.persistence.repository;


import com.autostore.inventory.application.port.driven.ProductRepository;
import com.autostore.inventory.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class InMemoryProductRepository implements ProductRepository {

    List<Product> products = new ArrayList<>();

    @Override
    public Product save(Product product) {
        Product toSave = toNewInstance(product);
        products.removeIf(p -> isSameProduct(p, toSave));
        products.add(toSave);
        return toNewInstance(toSave);
    }

    @Override
    public Boolean existsByCode(String code) {
        return products.stream()
                .anyMatch(product -> product.getCode().equals(code));
    }

    @Override
    public Optional<Product> findByCode(String code) {
        return products.stream().filter(product -> product.getCode().equals(code)).findFirst();
    }

    @Override
    public List<Product> findAllAvailableProducts() {
        // TODO
        return List.of();
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

    private boolean isSameProduct(Product a, Product b) {
        return a.getId().equals(b.getId());
    }

}
