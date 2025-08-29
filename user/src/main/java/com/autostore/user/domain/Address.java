package com.autostore.user.domain;


import com.autostore.user.domain.exception.ValidationException;


public record Address(
        Long id,
        String street,
        String number,
        String neighborhood,
        String city,
        String state,
        String zipCode,
        String complement,
        String country
) {

    private static final int MAX_FIELD_LENGTH = 100;

    public Address {
        validateRequiredField(street, "Street");
        validateRequiredField(number, "Number");
        validateRequiredField(city, "City");
        validateRequiredField(state, "State");
        validateRequiredField(country, "Country");
    }

    private static void validateRequiredField(String value, String fieldName) {
        if (isNullOrBlank(value))
            throw new ValidationException(fieldName + " cannot be null or empty");
        if (exceedsMaxLength(value))
            throw new ValidationException(fieldName + " cannot exceed " + MAX_FIELD_LENGTH + " characters");
    }

    private static boolean isNullOrBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private static boolean exceedsMaxLength(String value) {
        return value.length() > MAX_FIELD_LENGTH;
    }

}
