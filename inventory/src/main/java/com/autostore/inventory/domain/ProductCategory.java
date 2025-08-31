package com.autostore.inventory.domain;


import com.autostore.inventory.domain.exception.ValidationException;


public enum ProductCategory {
    VEHICLE,
    PARTS,
    ACCESSORIES;

    public static void validateValue(String value) {
        try {
            ProductCategory.valueOf(value);
        } catch (ValidationException e) {
            throw new ValidationException("Invalid product category: " + value);
        }
    }
}
