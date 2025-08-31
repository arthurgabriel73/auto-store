package com.autostore.validation.application.port.driver.model.command;


import com.autostore.validation.application.exception.ValidationException;


public record RegisterValidationProductCommand(String code) {

    public RegisterValidationProductCommand {
        validateCode(code);
    }

    private void validateCode(String code) {
        if (
                code == null
                        || code.isBlank()
                        || code.trim().isEmpty()
                        || code.length() > 100
        ) throw new ValidationException("Product code cannot be blank and must be less than 100 characters.");
    }

}
