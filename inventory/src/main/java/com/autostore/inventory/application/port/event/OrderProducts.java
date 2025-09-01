package com.autostore.inventory.application.port.event;


import com.autostore.inventory.domain.Product;


public record OrderProducts(
        Product product,
        int quantity
) {

}
