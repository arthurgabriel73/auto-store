package com.autostore.user.domain;


import com.autostore.user.domain.exception.ValidationException;
import lombok.Getter;


@Getter
public class FullName {

    private final String firstName;
    private final String lastName;

    private FullName(String firstName, String lastName) {
        validateFirstName(firstName);
        validateLastName(lastName);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static FullName of(String firstName, String lastName) {
        return new FullName(firstName, lastName);
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
