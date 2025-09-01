package com.autostore.inventory.infrastructure.adapter.driven.persistence;


import com.autostore.inventory.application.port.driven.ProductRepository;
import com.autostore.inventory.domain.Product;
import com.autostore.inventory.infrastructure.adapter.driven.persistence.entity.ProductDatabaseEntity;
import com.autostore.inventory.infrastructure.adapter.driven.persistence.repository.JpaProductRepository;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;


@Named
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository repository;

    @Override
    public Product save(Product product) {
        return repository.save(ProductDatabaseEntity.fromDomain(product)).toDomain();
    }

    @Override
    public Boolean existsByCode(String code) {
        return repository.existsByCode(code);
    }

}
