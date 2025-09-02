package com.autostore.bff.application.service.order.dto;


import com.autostore.bff.domain.order.Order;

import java.util.List;


public record ListOrdersResponse(List<Order> orders) {

}
