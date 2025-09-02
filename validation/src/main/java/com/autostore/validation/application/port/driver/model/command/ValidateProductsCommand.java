package com.autostore.validation.application.port.driver.model.command;


import com.autostore.validation.application.exception.ApplicationException;
import com.autostore.validation.application.port.event.Order;
import com.autostore.validation.domain.DomainEvent;


public record ValidateProductsCommand(DomainEvent<Order> event) {

    public ValidateProductsCommand {
        validateEvent(event);
    }

    private void validateEvent(DomainEvent<Order> event) {
        if (event == null) throw new ApplicationException("Event is null.");
    }

}
