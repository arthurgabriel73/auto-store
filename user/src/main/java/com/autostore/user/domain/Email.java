package com.autostore.user.domain;


import com.autostore.user.domain.exception.ValidationException;


public record Email(String value) {

    static String EMAIL_REGEX = "^(.+)@(\\S+)$";


    public Email {
        if (!validate(value)) throw new ValidationException("Email inválido");
    }

    boolean validate(String email) {
        return email.toLowerCase().matches(EMAIL_REGEX);
    }

}
