package com.autostore.inventory.infrastructure.adapter.driven.persistence.repository;


import com.autostore.inventory.infrastructure.adapter.driven.persistence.entity.ProductDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface JpaProductRepository extends JpaRepository<ProductDatabaseEntity, Long> {

    Boolean existsByCode(String code);

    Optional<ProductDatabaseEntity> findByCode(String code);

    @Query(
            value = "SELECT p.* " +
                    "FROM products p " +
                    "WHERE p.code in (SELECT i.product_code from inventory i WHERE available_quantity > 0)" +
                    "ORDER BY p.unit_value;",
            nativeQuery = true
    )
    List<ProductDatabaseEntity> findAllAvailableProducts();

}
