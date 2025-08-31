package com.autostore.validation.application.port.driver.model.command;


import com.autostore.validation.application.exception.ValidationException;


public record RegisterValidationProductCommand(String code, double unitValue) {

    public RegisterValidationProductCommand {
        validateCode(code);
        validateUnitValue(unitValue);
    }

    private void validateCode(String code) {
        if (code == null || code.isBlank()) throw new ValidationException("Product code cannot be null or blank.");
    }

    private void validateUnitValue(double unitValue) {
        if (unitValue <= 0) throw new ValidationException("Product unit value must be greater than zero.");
    }

}
