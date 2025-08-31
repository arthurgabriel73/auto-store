package com.autostore.validation.application.port.driver;


import com.autostore.validation.application.port.driver.model.command.ValidateProductsCommand;
import com.autostore.validation.domain.event.OrderEvent;


public interface ValidateProductsDriverPort {

    void execute(ValidateProductsCommand command);

    void rollback(OrderEvent event);

}
