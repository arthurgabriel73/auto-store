package com.autostore.bff.domain.order;


public record OrderProducts(
        Product product,
        int quantity
) {

}
