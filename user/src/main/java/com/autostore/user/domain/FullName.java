package com.autostore.user.domain;


import com.autostore.user.domain.exception.ValidationException;


public record FullName(String firstName, String lastName) {

    public FullName {
        validateFirstName(firstName);
        validateLastName(lastName);
    }

    private void validateFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty())
            throw new ValidationException("First name cannot be null or empty");
        if (firstName.length() < 2 || firstName.length() > 100)
            throw new ValidationException("First name must be between 2 and 100 characters");
        if (!firstName.matches("^[a-zA-ZÀ-ÿ '-]+$"))
            throw new ValidationException("First name contains invalid characters");
    }

    private void validateLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty())
            throw new ValidationException("Last name cannot be null or empty");
        if (lastName.length() < 2 || lastName.length() > 100)
            throw new ValidationException("Last name must be between 2 and 100 characters");
        if (!lastName.matches("^[a-zA-ZÀ-ÿ '-]+$"))
            throw new ValidationException("Last name contains invalid characters");
    }

    public String completeName() {
        return firstName + " " + lastName;
    }

}
