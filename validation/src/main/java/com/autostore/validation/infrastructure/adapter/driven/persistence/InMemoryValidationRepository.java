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
        Validation toSave = toNewInstance(validation);
        validations.removeIf(v -> isSameValidation(v, toSave));
        validations.add(toSave);
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

    @Override
    public List<String> listSuccessfulOrdersIds() {
        return List.of();
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

    private boolean isSameValidation(Validation a, Validation b) {
        return a.getId().equals(b.getId());
    }

}
