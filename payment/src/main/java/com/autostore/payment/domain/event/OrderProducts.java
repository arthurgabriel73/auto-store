package com.autostore.payment.domain.event;


public record OrderProducts(
        Product product,
        int quantity
) {

}
