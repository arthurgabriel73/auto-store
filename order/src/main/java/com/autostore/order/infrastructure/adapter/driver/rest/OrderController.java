package com.autostore.order.infrastructure.adapter.driver.rest;


import com.autostore.order.application.port.driver.CreateOrderDriverPort;
import com.autostore.order.application.port.driver.model.command.CreateOrderCommandOutput;
import com.autostore.order.infrastructure.adapter.driver.rest.dto.CreateOrderRequest;
import com.autostore.order.infrastructure.adapter.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderDriverPort createOrderDriverPort;
    private final JwtUtil jwtUtil;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderCommandOutput createOrder(@RequestBody CreateOrderRequest request) {
        String customerCpf = jwtUtil.extractUserId(request.getCustomer());
        var decodedRequest = new CreateOrderRequest(request.getProducts(), customerCpf);
        return createOrderDriverPort.execute(decodedRequest.toCommand());
    }

}
