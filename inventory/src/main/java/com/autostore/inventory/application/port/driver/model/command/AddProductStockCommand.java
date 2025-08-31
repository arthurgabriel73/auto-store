package com.autostore.inventory.application.port.driver.model.command;


public record AddProductStockCommand(
        String productCode,
        Integer quantity
) {

}
