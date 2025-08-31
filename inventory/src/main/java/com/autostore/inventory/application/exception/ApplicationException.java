package com.autostore.inventory.application.exception;


public class ApplicationException extends RuntimeException {

    private final String message;

    public ApplicationException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
