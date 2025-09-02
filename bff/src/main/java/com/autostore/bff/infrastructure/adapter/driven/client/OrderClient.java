package com.autostore.bff.infrastructure.adapter.driven.client;


import com.autostore.bff.application.port.driven.OrderGateway;
import com.autostore.bff.application.service.order.dto.CreateOrderRequest;
import com.autostore.bff.application.service.order.dto.CreateOrderResponse;
import com.autostore.bff.application.service.order.dto.ListOrdersResponse;
import jakarta.inject.Named;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Named
@FeignClient(name = "order-client", url = "${feign.client.config.order-service.url}")
public interface OrderClient extends OrderGateway {

    @Override
    @PostMapping
    CreateOrderResponse createOrder(CreateOrderRequest request);

    @Override
    @GetMapping("/by-ids")
    ListOrdersResponse listOrdersByIds(@RequestParam("ids") List<String> orderIds);

}
