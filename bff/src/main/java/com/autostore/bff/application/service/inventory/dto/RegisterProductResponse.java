package com.autostore.bff.application.service.inventory.dto;


public record RegisterProductResponse(
        Long productId,
        String productCode,
        Double unitValue,
        String category
) {

}
