package com.autostore.validation.application.port.event;


public record OrderProducts(
        Product product,
        int quantity
) {

}
