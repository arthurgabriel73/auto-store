package com.autostore.bff.application.service.inventory;


import com.autostore.bff.application.port.driven.InventoryGateway;
import com.autostore.bff.application.service.inventory.dto.*;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class InventoryService {

    private final InventoryGateway inventoryGateway;

    public RegisterProductResponse registerProduct(RegisterProductRequest request) {
        return inventoryGateway.registerProduct(request);
    }

    public void addProductStock(AddProductStockRequest request) {
        inventoryGateway.addProductStock(request);
    }

    public void updateProduct(UpdateProductRequest request) {
        inventoryGateway.updateProduct(request);
    }

    public ListAvailableProductsResponse listAvailableProducts() {
        return inventoryGateway.listAvailableProducts();
    }

    public ListSoldProductsResponse listSoldProducts() {
        return inventoryGateway.listSoldProducts();
    }

}
