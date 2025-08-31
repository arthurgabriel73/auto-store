package com.autostore.validation.domain.event;


public record OrderProducts(
        Product product,
        int quantity
) {

}
