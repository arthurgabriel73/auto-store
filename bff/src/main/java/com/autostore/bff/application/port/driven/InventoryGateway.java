package com.autostore.bff.application.port.driven;


import com.autostore.bff.application.service.inventory.dto.*;


public interface InventoryGateway {

    RegisterProductResponse registerProduct(RegisterProductRequest request);

    UpdateProductResponse updateProduct(UpdateProductRequest request);

    ListAvailableProductsResponse listAvailableProducts();

    ListSoldProductsResponse listSoldProducts();

}
