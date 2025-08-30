package com.autostore.user.domain;


import com.autostore.user.domain.exception.ValidationException;


public class Email {

    static String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*" +
            "(\\.[A-Za-z]{2,})$";
    private final String value;

    private Email(String value) {
        if (!validate(value)) throw new ValidationException("Email inválido");
        this.value = value.toLowerCase();
    }

    public static Email of(String value) {
        return new Email(value);
    }

    public String value() {
        return value;
    }

    boolean validate(String email) {
        if (email == null || email.isBlank()) return false;
        return email.toLowerCase().matches(EMAIL_REGEX);
    }

}
