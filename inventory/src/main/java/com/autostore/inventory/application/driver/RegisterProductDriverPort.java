package com.autostore.inventory.application.driver;


import com.autostore.inventory.application.driver.model.command.RegisterProductCommand;
import com.autostore.inventory.application.driver.model.command.RegisterProductCommandOutput;


public interface RegisterProductDriverPort {

    RegisterProductCommandOutput execute(RegisterProductCommand command);

}
