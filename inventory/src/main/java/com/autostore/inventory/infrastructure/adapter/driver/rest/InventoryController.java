package com.autostore.inventory.infrastructure.adapter.driver.rest;


import com.autostore.inventory.application.port.driver.AddProductStockDriverPort;
import com.autostore.inventory.application.port.driver.RegisterProductDriverPort;
import com.autostore.inventory.application.port.driver.model.command.AddProductStockCommand;
import com.autostore.inventory.application.port.driver.model.command.RegisterProductCommand;
import com.autostore.inventory.application.port.driver.model.command.RegisterProductCommandOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final RegisterProductDriverPort registerProductDriverPort;
    private final AddProductStockDriverPort addProductStockDriverPort;

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterProductCommandOutput registerProduct(RegisterProductCommand command) {
        return registerProductDriverPort.execute(command);
    }

    @PostMapping("/product/stock")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addProductStock(AddProductStockCommand command) {
        addProductStockDriverPort.execute(command);
    }

}
