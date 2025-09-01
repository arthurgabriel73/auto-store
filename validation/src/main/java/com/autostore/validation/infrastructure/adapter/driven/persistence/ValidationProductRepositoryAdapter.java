package com.autostore.validation.infrastructure.adapter.driven.persistence;


import com.autostore.validation.application.port.driven.ValidationProductRepository;
import com.autostore.validation.domain.ValidationProduct;
import com.autostore.validation.infrastructure.adapter.driven.persistence.entity.ValidationProductDatabaseEntity;
import com.autostore.validation.infrastructure.adapter.driven.persistence.repository.JpaValidationProductRepository;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;


@Named
@RequiredArgsConstructor
public class ValidationProductRepositoryAdapter implements ValidationProductRepository {

    private final JpaValidationProductRepository repository;

    @Override
    public ValidationProduct save(ValidationProduct validationProduct) {
        return repository.save(ValidationProductDatabaseEntity.fromDomain(validationProduct)).toDomain();
    }

    @Override
    public Boolean existsByCode(String code) {
        return repository.existsByCode(code);
    }

}
