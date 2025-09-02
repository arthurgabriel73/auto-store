package com.autostore.bff.application.service.order.dto;


import com.autostore.bff.domain.order.OrderProducts;
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

}
