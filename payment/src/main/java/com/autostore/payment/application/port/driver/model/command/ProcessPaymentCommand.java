package com.autostore.payment.application.port.driver.model.command;


import com.autostore.payment.application.exception.ValidationException;
import com.autostore.payment.application.port.event.Order;
import com.autostore.payment.domain.DomainEvent;


public record ProcessPaymentCommand(DomainEvent<Order> event) {

    public ProcessPaymentCommand {
        validateEvent(event);
    }

    private void validateEvent(DomainEvent<Order> event) {
        if (event == null) throw new ValidationException("Event is null.");
    }

}
