package com.autostore.inventory.application.port.driver;


import com.autostore.inventory.application.port.driver.model.command.AddProductStockCommand;


public interface AddProductStockDriverPort {

    void execute(AddProductStockCommand command);

}
