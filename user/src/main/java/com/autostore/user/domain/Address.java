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

    public Address {
        validateStreet(street);
        validateNumber(number);
        validateCity(city);
        validateState(state);
        validateCountry(country);
    }

    private void validateStreet(String street) {
        if (street == null || street.trim().isEmpty() || street.length() > 100)
            throw new ValidationException("Street must be between 1 and 100 characters");
    }

    private void validateNumber(String number) {
        if (number == null || number.trim().isEmpty() || number.length() > 100)
            throw new ValidationException("Number must be between 1 and 100 characters");
    }

    private void validateCity(String city) {
        if (city == null || city.trim().isEmpty() || city.length() > 100)
            throw new ValidationException("City must be between 1 and 100 characters");
    }

    private void validateState(String state) {
        if (state == null || state.trim().isEmpty() || state.length() > 100)
            throw new ValidationException("State must be between 1 and 100 characters");
    }

    private void validateCountry(String country) {
        if (country == null || country.trim().isEmpty() || country.length() > 100)
            throw new ValidationException("Country must be between 1 and 100 characters");
    }

}
