package com.autostore.inventory.domain.event;


import com.autostore.inventory.domain.Product;


public record OrderProducts(
        Product product,
        int quantity
) {

}
