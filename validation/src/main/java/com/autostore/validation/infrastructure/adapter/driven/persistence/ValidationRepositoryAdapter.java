package com.autostore.validation.infrastructure.adapter.driven.persistence;


import com.autostore.validation.application.port.driven.ValidationRepository;
import com.autostore.validation.domain.Validation;
import com.autostore.validation.infrastructure.adapter.driven.persistence.entity.ValidationDatabaseEntity;
import com.autostore.validation.infrastructure.adapter.driven.persistence.repository.JpaValidationRepository;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;


@Named
@RequiredArgsConstructor
public class ValidationRepositoryAdapter implements ValidationRepository {

    private final JpaValidationRepository repository;

    @Override
    public void save(Validation validation) {
        repository.save(ValidationDatabaseEntity.fromDomain(validation));
    }

    @Override
    public Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId) {
        return repository.existsByOrderIdAndTransactionId(orderId, transactionId);
    }

    @Override
    public Optional<Validation> findByOrderIdAndTransactionId(String orderId, String transactionId) {
        var entity = repository.findByOrderIdAndTransactionId(orderId, transactionId);
        if (entity.isEmpty()) return Optional.empty();
        return entity.map(ValidationDatabaseEntity::toDomain);
    }

    @Override
    public List<String> listSuccessfulOrdersIds() {
        return repository.findBySuccess(true);
    }

}
