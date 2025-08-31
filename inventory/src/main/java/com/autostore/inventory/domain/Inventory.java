package com.autostore.inventory.domain;


import com.autostore.inventory.domain.exception.InventoryOutOfStockException;
import com.autostore.inventory.domain.exception.ValidationException;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class Inventory {

    private final Long id;
    private final String productCode;
    private Integer availableQuantity;


    public void increaseQuantityBy(int amount) {
        if (amount < 1) throw new ValidationException("Amount to increase must be a positive integer");
        this.availableQuantity += amount;
    }

    public void decreaseQuantityBy(int amount) {
        if (amount < 1) throw new ValidationException("Amount to decrease must be a positive integer");
        if (this.availableQuantity - amount < 0)
            throw new InventoryOutOfStockException("Insufficient stock to decrease by the specified amount");
        this.availableQuantity -= amount;
    }

}
