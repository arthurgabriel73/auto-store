package com.autostore.user.application.port.driver;


import com.autostore.user.application.port.driver.model.command.AuthenticateUserCommand;
import com.autostore.user.application.port.driver.model.command.AuthenticateUserCommandOutput;


public interface AuthenticateUserUserDriverPort {

    AuthenticateUserCommandOutput execute(AuthenticateUserCommand command);

}
