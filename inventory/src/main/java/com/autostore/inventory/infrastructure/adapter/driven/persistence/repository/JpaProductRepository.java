package com.autostore.inventory.infrastructure.adapter.driven.persistence.repository;


import com.autostore.inventory.infrastructure.adapter.driven.persistence.entity.ProductDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaProductRepository extends JpaRepository<ProductDatabaseEntity, Long> {

    Boolean existsByCode(String code);

}
