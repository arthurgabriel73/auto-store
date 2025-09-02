package com.autostore.validation.application.port.driver;


import com.autostore.validation.application.port.driver.model.command.ValidateProductsCommand;
import com.autostore.validation.application.port.event.Order;
import com.autostore.validation.domain.DomainEvent;


public interface ValidateProductsDriverPort {

    void execute(ValidateProductsCommand command);

    void rollback(DomainEvent<Order> event);

}
