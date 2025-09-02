package com.autostore.order.application.port.driver.model.command;


import com.autostore.order.domain.OrderProducts;

import java.util.List;


public record CreateOrderCommand(List<OrderProducts> products, String customer) {

}
