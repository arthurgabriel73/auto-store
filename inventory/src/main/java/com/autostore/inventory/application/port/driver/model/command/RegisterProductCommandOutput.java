package com.autostore.inventory.application.port.driver.model.command;


import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class RegisterProductCommandOutput {

    private String productId;
    private String productCode;
    private Double unitValue;
    private String category;

    private RegisterProductCommandOutput(String productId, String productCode, Double unitValue, String category) {
        this.productId = productId;
        this.productCode = productCode;
        this.unitValue = unitValue;
        this.category = category;
    }

    public static RegisterProductCommandOutput of(String productId, String productCode, Double unitValue, String category) {
        return new RegisterProductCommandOutput(productId, productCode, unitValue, category);
    }

}
