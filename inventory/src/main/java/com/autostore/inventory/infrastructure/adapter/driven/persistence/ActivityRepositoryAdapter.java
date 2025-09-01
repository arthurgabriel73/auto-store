package com.autostore.inventory.infrastructure.adapter.driven.persistence;


import com.autostore.inventory.application.port.driven.ActivityRepository;
import com.autostore.inventory.domain.Activity;
import com.autostore.inventory.infrastructure.adapter.driven.persistence.entity.ActivityDatabaseEntity;
import com.autostore.inventory.infrastructure.adapter.driven.persistence.repository.JpaActivityRepository;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Named
@RequiredArgsConstructor
public class ActivityRepositoryAdapter implements ActivityRepository {

    private final JpaActivityRepository repository;

    @Override
    public Activity save(Activity activity) {
        return repository.save(ActivityDatabaseEntity.fromDomain(activity)).toDomain();
    }

    @Override
    public List<Activity> findByOrderIdAndTransactionId(String orderId, String transactionId) {
        return repository.findByOrderIdAndTransactionId(orderId, transactionId)
                .stream()
                .map(ActivityDatabaseEntity::toDomain)
                .toList();
    }

    @Override
    public Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId) {
        return repository.existsByOrderIdAndTransactionId(orderId, transactionId);
    }

}
