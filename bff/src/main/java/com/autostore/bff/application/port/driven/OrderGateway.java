package com.autostore.bff.application.port.driven;


import com.autostore.bff.application.service.order.dto.CreateOrderRequest;
import com.autostore.bff.application.service.order.dto.CreateOrderResponse;


public interface OrderGateway {

    CreateOrderResponse createOrder(CreateOrderRequest request);

}
