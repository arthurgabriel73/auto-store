package com.autostore.validation.application.port.driver;


import com.autostore.validation.application.port.driver.model.command.RegisterValidationProductCommand;


public interface RegisterValidationProductDriverPort {

    void execute(RegisterValidationProductCommand command);

}
