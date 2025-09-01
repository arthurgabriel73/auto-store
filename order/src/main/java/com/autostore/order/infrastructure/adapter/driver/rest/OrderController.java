package com.autostore.order.infrastructure.adapter.driver.rest;


import com.autostore.order.application.port.driver.CreateOrderDriverPort;
import com.autostore.order.application.port.driver.model.command.CreateOrderCommandOutput;
import com.autostore.order.infrastructure.adapter.driver.rest.dto.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderDriverPort createOrderDriverPort;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderCommandOutput createOrder(@RequestBody CreateOrderRequest request) {
        return createOrderDriverPort.execute(request.toCommand());
    }

}
