package com.autostore.bff.application.service.inventory.dto;


import com.autostore.bff.application.service.order.dto.ListOrdersResponse;
import com.autostore.bff.domain.order.OrderProducts;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class ListSoldProductsResponse {

    @Getter
    private List<OrderProducts> soldProducts;

    public ListSoldProductsResponse(ListOrdersResponse ordersResponse) {
        this.soldProducts = getOrderedSoldProducts(ordersResponse);
    }

    private List<OrderProducts> getOrderedSoldProducts(ListOrdersResponse ordersResponse) {
        return ordersResponse.orders().stream()
                .flatMap(order -> order.products().stream())
                .sorted(Comparator.comparingDouble(p -> p.product().unitValue()))
                .collect(Collectors.toList());
    }

}
