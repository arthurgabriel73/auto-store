package com.autostore.order.infrastructure.adapter.driver.rest.dto;


import com.autostore.order.application.port.driver.model.command.CreateOrderCommand;
import com.autostore.order.domain.OrderProducts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    private List<OrderProducts> products;
    private String customer;

    public CreateOrderCommand toCommand() {
        return new CreateOrderCommand(products, customer);
    }

}
