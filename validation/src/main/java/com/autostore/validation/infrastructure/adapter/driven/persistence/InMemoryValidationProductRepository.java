package com.autostore.validation.infrastructure.adapter.driven.persistence;


import com.autostore.validation.application.port.driven.ValidationProductRepository;
import com.autostore.validation.domain.ValidationProduct;

import java.util.ArrayList;
import java.util.List;


public class InMemoryValidationProductRepository implements ValidationProductRepository {

    List<ValidationProduct> products = new ArrayList<>();

    @Override
    public ValidationProduct save(ValidationProduct validationProduct) {
        ValidationProduct toSave = toNewInstance(validationProduct);
        products.removeIf(p -> isSameProduct(p, toSave));
        products.add(toSave);
        return toNewInstance(toSave);
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

    private boolean isSameProduct(ValidationProduct a, ValidationProduct b) {
        return a.id().equals(b.id());
    }

}
