package com.autostore.user.domain;


import com.autostore.user.domain.exception.ValidationException;
import lombok.Getter;


@Getter
public class Address {

    private static final int MAX_FIELD_LENGTH = 100;
    private final Long id;
    private final String street;
    private final String number;
    private final String neighborhood;
    private final String city;
    private final String state;
    private final String zipCode;
    private final String complement;
    private final String country;

    private Address(Long id, String street, String number, String neighborhood, String city, String state, String zipCode, String complement, String country
    ) {
        validateRequiredField(street, "Street");
        validateRequiredField(number, "Number");
        validateRequiredField(city, "City");
        validateRequiredField(state, "State");
        validateRequiredField(country, "Country");
        this.id = id;
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.complement = complement;
        this.country = country;
    }

    public static Address create(String street, String number, String neighborhood, String city, String state, String zipCode, String complement, String country) {
        return new Address(null, street, number, neighborhood, city, state, zipCode, complement, country);
    }

    public static Address of(Long id, String street, String number, String neighborhood, String city, String state, String zipCode, String complement, String country) {
        return new Address(id, street, number, neighborhood, city, state, zipCode, complement, country);
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
