package com.autostore.validation.infrastructure.adapter.driven.persistence;


import com.autostore.validation.application.port.driven.ValidationRepository;
import com.autostore.validation.domain.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class InMemoryValidationRepository implements ValidationRepository {

    List<Validation> validations = new ArrayList<>();

    @Override
    public void save(Validation validation) {
        var newValidation = toNewInstance(validation);
        validations.add(newValidation);
    }

    @Override
    public Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId) {
        return validations.stream()
                .anyMatch(v -> v.getOrderId().equals(orderId) && v.getTransactionId().equals(transactionId));
    }

    @Override
    public Optional<Validation> findByOrderIdAndTransactionId(String orderId, String transactionId) {
        return validations.stream()
                .filter(v -> v.getOrderId().equals(orderId) && v.getTransactionId().equals(transactionId))
                .findFirst()
                .map(this::toNewInstance);
    }

    private Validation toNewInstance(Validation validation) {
        return Validation
                .builder()
                .id(validation.getId() != null ? validation.getId() : validations.size() + 1)
                .orderId(validation.getOrderId())
                .transactionId(validation.getTransactionId())
                .createdAt(validation.getCreatedAt())
                .updatedAt(validation.getUpdatedAt())
                .success(validation.isSuccess())
                .build();
    }

}
