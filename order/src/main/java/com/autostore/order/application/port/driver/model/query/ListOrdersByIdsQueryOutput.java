package com.autostore.order.application.port.driver.model.query;


import com.autostore.order.domain.Order;

import java.util.List;


public record ListOrdersByIdsQueryOutput(List<Order> orders) {

}
