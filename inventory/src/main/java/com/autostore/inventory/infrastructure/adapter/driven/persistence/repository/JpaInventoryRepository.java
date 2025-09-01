package com.autostore.inventory.infrastructure.adapter.driven.persistence.repository;


import com.autostore.inventory.infrastructure.adapter.driven.persistence.entity.InventoryDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface JpaInventoryRepository extends JpaRepository<InventoryDatabaseEntity, Long> {

    Optional<InventoryDatabaseEntity> findByProductCode(String productCode);

}
