package com.autostore.validation.infrastructure.adapter.driven.persistence;


import com.autostore.validation.application.port.driven.ValidationProductRepository;
import com.autostore.validation.domain.ValidationProduct;

import java.util.ArrayList;
import java.util.List;


public class InMemoryValidationProductRepository implements ValidationProductRepository {

    List<ValidationProduct> products = new ArrayList<>();

    @Override
    public ValidationProduct save(ValidationProduct validationProduct) {
        var newProduct = toNewInstance(validationProduct);
        products.add(newProduct);
        return toNewInstance(newProduct);
    }

    @Override
    public Boolean existsByCode(String code) {
        return products.stream().anyMatch(product -> product.code().equals(code));
    }

    private ValidationProduct toNewInstance(ValidationProduct product) {
        return ValidationProduct
                .builder()
                .id(product.id() != null ? product.id() : products.size() + 1)
                .code(product.code())
                .build();
    }

}
