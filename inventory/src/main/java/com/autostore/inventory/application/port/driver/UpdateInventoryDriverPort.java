package com.autostore.inventory.application.port.driver;


import com.autostore.inventory.application.port.driver.model.command.UpdateInventoryCommand;
import com.autostore.inventory.application.port.event.OrderEvent;


public interface UpdateInventoryDriverPort {

    void execute(UpdateInventoryCommand command);

    void rollback(OrderEvent event);

}
