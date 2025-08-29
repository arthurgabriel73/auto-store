package com.autostore.user.domain.exception;


import lombok.Setter;


@Setter
public class BusinessException extends RuntimeException {

    private String message;

    public BusinessException(String message) {

        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {

        return message;
    }

}
