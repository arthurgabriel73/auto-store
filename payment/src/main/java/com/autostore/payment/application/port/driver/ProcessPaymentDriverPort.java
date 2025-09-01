package com.autostore.payment.application.port.driver;


import com.autostore.payment.application.port.driver.model.command.ProcessPaymentCommand;
import com.autostore.payment.domain.event.OrderEvent;


public interface ProcessPaymentDriverPort {

    void execute(ProcessPaymentCommand command);

    void rollback(OrderEvent event);

}
