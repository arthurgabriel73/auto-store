package com.autostore.inventory.application.port.driver.model.command;


import com.autostore.inventory.domain.event.OrderEvent;
import com.autostore.inventory.domain.exception.ValidationException;


public record UpdateInventoryCommand(OrderEvent event) {

    public UpdateInventoryCommand {
        validateEvent(event);
    }

    private void validateEvent(OrderEvent event) {
        if (event == null) throw new ValidationException("Event is null.");
    }

}
