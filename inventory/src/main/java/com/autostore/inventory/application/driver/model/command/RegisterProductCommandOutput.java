package com.autostore.inventory.application.driver.model.command;


import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class RegisterProductCommandOutput {

    private String productId;
    private String productCode;
    private Double unitValue;
    private String category;
    private Integer quantity;

    private RegisterProductCommandOutput(String productId, String productCode, Double unitValue, String category, Integer quantity) {
        this.productId = productId;
        this.productCode = productCode;
        this.unitValue = unitValue;
        this.category = category;
        this.quantity = quantity;
    }

    public static RegisterProductCommandOutput of(String productId, String productCode, Double unitValue, String category, Integer quantity) {
        return new RegisterProductCommandOutput(productId, productCode, unitValue, category, quantity);
    }

}
