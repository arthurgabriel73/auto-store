package com.autostore.order.application.port.driver.model.command;


import com.autostore.order.domain.Order;


public record CreateOrderCommandOutput(Order order) {

}
