package com.autostore.bff.application.port.event;


public record OrderProducts(
        Product product,
        int quantity
) {

}
