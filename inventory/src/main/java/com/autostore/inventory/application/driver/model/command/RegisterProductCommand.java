package com.autostore.inventory.application.driver.model.command;


import com.autostore.inventory.domain.ProductCategory;

import java.util.Map;


public record RegisterProductCommand(
        Long id,
        String code,
        Double unitValue,
        ProductCategory category,
        Map<String, Object> details
) {

}
