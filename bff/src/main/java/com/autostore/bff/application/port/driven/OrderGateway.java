package com.autostore.bff.application.port.driven;


import com.autostore.bff.application.service.order.dto.CreateOrderRequest;
import com.autostore.bff.application.service.order.dto.CreateOrderResponse;
import com.autostore.bff.application.service.order.dto.ListOrdersResponse;

import java.util.List;


public interface OrderGateway {

    CreateOrderResponse createOrder(CreateOrderRequest request);

    ListOrdersResponse listOrdersByIds(List<String> orderIds);

}
