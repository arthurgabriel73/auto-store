package com.autostore.bff.infrastructure.adapter.driven.client;


import com.autostore.bff.application.port.driven.OrderGateway;
import com.autostore.bff.application.service.order.dto.CreateOrderRequest;
import com.autostore.bff.application.service.order.dto.CreateOrderResponse;
import jakarta.inject.Named;


@Named
public class OrderClient implements OrderGateway {

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        return null;
    }

}
