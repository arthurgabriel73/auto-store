package com.autostore.bff.application.service.inventory.dto;


public record ProductResponse(
        Long productId,
        String productCode,
        Double unitValue,
        String category
) {

}
