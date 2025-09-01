package com.autostore.payment.application.port.driver.model.command;


import com.autostore.payment.application.exception.ValidationException;
import com.autostore.payment.domain.event.OrderEvent;


public record ProcessPaymentCommand(OrderEvent event) {

    public ProcessPaymentCommand {
        validateEvent(event);
    }

    private void validateEvent(OrderEvent event) {
        if (event == null) throw new ValidationException("Event is null.");
    }

}
