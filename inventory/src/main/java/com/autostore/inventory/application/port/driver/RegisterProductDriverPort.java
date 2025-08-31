package com.autostore.inventory.application.port.driver;


import com.autostore.inventory.application.port.driver.model.command.RegisterProductCommand;
import com.autostore.inventory.application.port.driver.model.command.RegisterProductCommandOutput;


public interface RegisterProductDriverPort {

    RegisterProductCommandOutput execute(RegisterProductCommand command);

}
