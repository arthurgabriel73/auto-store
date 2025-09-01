package com.autostore.bff.infrastructure.adapter.driven.client;


import com.autostore.bff.application.port.driven.InventoryGateway;
import com.autostore.bff.application.service.inventory.dto.*;
import jakarta.inject.Named;


@Named
public class InventoryClient implements InventoryGateway {

    @Override
    public RegisterProductResponse registerProduct(RegisterProductRequest request) {
        return null;
    }

    @Override
    public void updateProduct(UpdateProductRequest request) {
    }

    @Override
    public ListAvailableProductsResponse listAvailableProducts() {
        return null;
    }

    @Override
    public ListSoldProductsResponse listSoldProducts() {
        return null;
    }

}
