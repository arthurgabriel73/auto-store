package com.autostore.user.application.port.driver;


import com.autostore.user.application.port.driver.model.command.RegisterUserCommand;
import com.autostore.user.application.port.driver.model.command.RegisterUserCommandOutput;


public interface RegisterUserDriverPort {

    RegisterUserCommandOutput execute(RegisterUserCommand command);

}
