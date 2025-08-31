package com.autostore.inventory.domain.exception;


public class InventoryOutOfStockException extends BusinessException {

    public InventoryOutOfStockException(String message) {
        super(message);
    }

}
