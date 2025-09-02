package com.autostore.inventory.infrastructure.adapter.driver.rest;


import com.autostore.inventory.application.port.driver.AddProductStockDriverPort;
import com.autostore.inventory.application.port.driver.ListAvailableProductsDriverPort;
import com.autostore.inventory.application.port.driver.RegisterProductDriverPort;
import com.autostore.inventory.application.port.driver.UpdateProductDriverPort;
import com.autostore.inventory.application.port.driver.model.command.AddProductStockCommand;
import com.autostore.inventory.application.port.driver.model.command.RegisterProductCommand;
import com.autostore.inventory.application.port.driver.model.command.RegisterProductCommandOutput;
import com.autostore.inventory.application.port.driver.model.command.UpdateProductCommand;
import com.autostore.inventory.application.port.driver.model.query.ListAvailableProductsQueryOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final RegisterProductDriverPort registerProductDriverPort;
    private final UpdateProductDriverPort updateProductDriverPort;
    private final AddProductStockDriverPort addProductStockDriverPort;
    private final ListAvailableProductsDriverPort listAvailableProductsDriverPort;

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterProductCommandOutput registerProduct(@RequestBody RegisterProductCommand command) {
        return registerProductDriverPort.execute(command);
    }

    @PutMapping("/product")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProduct(@RequestBody UpdateProductCommand command) {
        updateProductDriverPort.execute(command);
    }

    @PostMapping("/product/stock")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addProductStock(@RequestBody AddProductStockCommand command) {
        addProductStockDriverPort.execute(command);
    }

    @GetMapping("/available-products")
    public ListAvailableProductsQueryOutput listAvailableProducts() {
        return listAvailableProductsDriverPort.query();
    }

}
