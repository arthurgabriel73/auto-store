package com.autostore.inventory.application.port.driver;


import com.autostore.inventory.application.port.driver.model.command.UpdateInventoryCommand;
import com.autostore.inventory.application.port.event.Order;
import com.autostore.inventory.domain.DomainEvent;


public interface UpdateInventoryDriverPort {

    void execute(UpdateInventoryCommand command);

    void rollback(DomainEvent<Order> event);

}
