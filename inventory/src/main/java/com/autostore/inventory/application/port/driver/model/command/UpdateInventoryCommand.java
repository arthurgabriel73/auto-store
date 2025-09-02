package com.autostore.inventory.application.port.driver.model.command;


import com.autostore.inventory.application.port.event.Order;
import com.autostore.inventory.domain.DomainEvent;
import com.autostore.inventory.domain.exception.ValidationException;


public record UpdateInventoryCommand(DomainEvent<Order> event) {

    public UpdateInventoryCommand {
        validateEvent(event);
    }

    private void validateEvent(DomainEvent<Order> event) {
        if (event == null) throw new ValidationException("Event is null.");
    }

}
