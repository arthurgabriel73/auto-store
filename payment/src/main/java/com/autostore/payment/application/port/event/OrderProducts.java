package com.autostore.payment.application.port.event;


public record OrderProducts(
        Product product,
        int quantity
) {

}
