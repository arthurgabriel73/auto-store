package com.autostore.bff.application.service.order;


import com.autostore.bff.application.port.driven.OrderGateway;
import com.autostore.bff.application.service.order.dto.CreateOrderRequest;
import com.autostore.bff.application.service.order.dto.CreateOrderResponse;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class OrderService {

    private final OrderGateway orderGateway;

    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        return orderGateway.createOrder(request);
    }

}
