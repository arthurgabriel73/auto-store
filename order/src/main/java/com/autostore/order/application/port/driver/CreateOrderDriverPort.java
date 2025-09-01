package com.autostore.order.application.port.driver;


import com.autostore.order.application.port.driver.model.command.CreateOrderCommand;
import com.autostore.order.application.port.driver.model.command.CreateOrderCommandOutput;


public interface CreateOrderDriverPort {

    CreateOrderCommandOutput execute(CreateOrderCommand command);

}
