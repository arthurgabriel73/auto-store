package com.autostore.inventory.domain;


import com.autostore.inventory.domain.exception.ValidationException;
import lombok.Getter;


@Getter
public enum ProductCategory {
    VEHICLE("vehicle"),
    PARTS("parts"),
    ACCESSORIES("accessories"),
    ;

    private final String value;

    ProductCategory(String value) {
        this.value = value;
    }

    public static void validateValue(String value) {
        try {
            ProductCategory.valueOf(value);
        } catch (ValidationException e) {
            throw new ValidationException("Invalid product category: " + value);
        }
    }
}
