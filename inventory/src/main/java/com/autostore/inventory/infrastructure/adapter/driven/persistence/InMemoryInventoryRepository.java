package com.autostore.inventory.infrastructure.adapter.driven.persistence;


import com.autostore.inventory.application.port.driven.InventoryRepository;
import com.autostore.inventory.domain.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class InMemoryInventoryRepository implements InventoryRepository {

    List<Inventory> inventoryList = new ArrayList<>();

    @Override
    public Inventory save(Inventory inventory) {
        Inventory newInventory = toNewInstance(inventory);
        inventoryList.add(newInventory);
        return toNewInstance(newInventory);
    }

    @Override
    public Optional<Inventory> findByProductCode(String productCode) {
        return inventoryList.stream().filter(inventory -> inventory.getProductCode().equals(productCode)).findFirst();
    }

    private Inventory toNewInstance(Inventory inventory) {
        return Inventory
                .builder()
                .id(inventory.getId() != null ? inventory.getId() : inventoryList.size() + 1)
                .productCode(inventory.getProductCode())
                .availableQuantity(inventory.getAvailableQuantity())
                .build();
    }

}
