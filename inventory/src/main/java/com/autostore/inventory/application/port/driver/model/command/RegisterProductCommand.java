package com.autostore.inventory.application.port.driver.model.command;


import com.autostore.inventory.domain.ProductCategory;

import java.util.Map;


public record RegisterProductCommand(
        String code,
        Double unitValue,
        ProductCategory category,
        Map<String, Object> details
) {

}
