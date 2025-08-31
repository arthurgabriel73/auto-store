package com.autostore.inventory.application.usecase;


import com.autostore.inventory.application.port.driver.AddProductStockDriverPort;
import com.autostore.inventory.application.port.driver.model.command.AddProductStockCommand;


public class AddProductStockUseCase implements AddProductStockDriverPort {

    @Override
    public void execute(AddProductStockCommand command) {
    }

}
