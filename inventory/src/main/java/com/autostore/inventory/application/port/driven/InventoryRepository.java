package com.autostore.inventory.application.port.driven;


import com.autostore.inventory.domain.Inventory;

import java.util.Optional;


public interface InventoryRepository {

    Inventory save(Inventory inventory);

    Optional<Inventory> findByProductCode(String productCode);

}
