package com.autostore.bff.infrastructure.adapter.driven.client;


import com.autostore.bff.application.port.driven.InventoryGateway;
import com.autostore.bff.application.service.inventory.dto.*;
import jakarta.inject.Named;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@Named
@FeignClient(name = "inventory-client", url = "${feign.client.config.inventory-service.url}")
public interface InventoryClient extends InventoryGateway {

    @Override
    @PostMapping("/product")
    ProductResponse registerProduct(RegisterProductRequest request);

    @Override
    @PostMapping("/product/stock")
    void addProductStock(AddProductStockRequest request);

    @Override
    @PutMapping("/product")
    void updateProduct(UpdateProductRequest request);

    @Override
    @GetMapping("/available-products")
    ListAvailableProductsResponse listAvailableProducts();

    @Override
    @GetMapping("/products/sold")
    ListSoldProductsResponse listSoldProducts();

}
