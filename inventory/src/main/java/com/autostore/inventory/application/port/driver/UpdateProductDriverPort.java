package com.autostore.inventory.application.port.driver;


import com.autostore.inventory.application.port.driver.model.command.UpdateProductCommand;


public interface UpdateProductDriverPort {

    void execute(UpdateProductCommand command);

}
