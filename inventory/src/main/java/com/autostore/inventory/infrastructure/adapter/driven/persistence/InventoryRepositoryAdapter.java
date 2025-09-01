package com.autostore.inventory.infrastructure.adapter.driven.persistence;


import com.autostore.inventory.application.port.driven.InventoryRepository;
import com.autostore.inventory.domain.Inventory;
import com.autostore.inventory.infrastructure.adapter.driven.persistence.entity.InventoryDatabaseEntity;
import com.autostore.inventory.infrastructure.adapter.driven.persistence.repository.JpaInventoryRepository;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@Named
@RequiredArgsConstructor
public class InventoryRepositoryAdapter implements InventoryRepository {

    private final JpaInventoryRepository repository;

    @Override
    public Inventory save(Inventory inventory) {
        return repository.save(InventoryDatabaseEntity.fromDomain(inventory)).toDomain();
    }

    @Override
    public Optional<Inventory> findByProductCode(String productCode) {
        var inventory = repository.findByProductCode(productCode);
        if (inventory.isEmpty()) return Optional.empty();
        return inventory.map(InventoryDatabaseEntity::toDomain);
    }

}
