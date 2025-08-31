package com.autostore.inventory.application.usecase;


import com.autostore.inventory.application.exception.ApplicationException;
import com.autostore.inventory.application.exception.InventoryNotFoundException;
import com.autostore.inventory.application.port.driven.InventoryRepository;
import com.autostore.inventory.application.port.driven.ProductRepository;
import com.autostore.inventory.application.port.driver.AddProductStockDriverPort;
import com.autostore.inventory.application.port.driver.model.command.AddProductStockCommand;
import com.autostore.inventory.domain.Inventory;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class AddProductStockUseCase implements AddProductStockDriverPort {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public void execute(AddProductStockCommand command) {
        requireProductExists(command.productCode());
        addStock(command);
    }

    private void requireProductExists(String productCode) {
        if (!productRepository.existsByCode(productCode))
            throw new ApplicationException("Product with code " + productCode + " does not exist.");
    }


    private void addStock(AddProductStockCommand command) {
        Inventory inventory = inventoryRepository.findByProductCode(command.productCode()).orElseThrow(() ->
                new InventoryNotFoundException("Inventory not found for product code: " + command.productCode()));
        inventory.increaseQuantityBy(command.quantity());
        inventoryRepository.save(inventory);
    }

}
