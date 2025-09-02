package com.autostore.bff.application.service.inventory.dto;


import java.util.Map;


public record ProductResponseWithDetails(
        String code,
        Double unitValue,
        String category,
        Map<String, Object> details
) {

}
