package com.autostore.validation.application.port.driver.model.command;


import com.autostore.validation.application.exception.ApplicationException;
import com.autostore.validation.domain.event.OrderEvent;


public record ValidateProductsCommand(OrderEvent event) {

    public ValidateProductsCommand {
        validateEvent(event);
    }

    private void validateEvent(OrderEvent event) {
        if (event == null) throw new ApplicationException("Event is null.");
    }

}
