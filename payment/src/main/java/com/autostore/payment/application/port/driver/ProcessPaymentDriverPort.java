package com.autostore.payment.application.port.driver;


import com.autostore.payment.application.port.driver.model.command.ProcessPaymentCommand;
import com.autostore.payment.application.port.event.Order;
import com.autostore.payment.domain.DomainEvent;


public interface ProcessPaymentDriverPort {

    void execute(ProcessPaymentCommand command);

    void rollback(DomainEvent<Order> event);

}
