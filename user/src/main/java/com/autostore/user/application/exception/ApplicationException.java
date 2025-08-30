package com.autostore.user.application.exception;


import lombok.Setter;


@Setter
public class ApplicationException extends RuntimeException {

    private String message;

    public ApplicationException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
