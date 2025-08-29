package com.autostore.user.domain;


import com.autostore.user.domain.exception.ValidationException;


public record Email(String value) {

    static String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*" +
            "(\\.[A-Za-z]{2,})$";


    public Email {
        if (!validate(value)) throw new ValidationException("Email inválido");
    }

    boolean validate(String email) {
        if (email == null || email.isBlank()) return false;
        return email.toLowerCase().matches(EMAIL_REGEX);
    }

}
