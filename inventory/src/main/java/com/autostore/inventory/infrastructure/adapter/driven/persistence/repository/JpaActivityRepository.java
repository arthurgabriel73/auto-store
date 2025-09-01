package com.autostore.inventory.infrastructure.adapter.driven.persistence.repository;


import com.autostore.inventory.infrastructure.adapter.driven.persistence.entity.ActivityDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface JpaActivityRepository extends JpaRepository<ActivityDatabaseEntity, Long> {

    List<ActivityDatabaseEntity> findByOrderIdAndTransactionId(String orderId, String transactionId);

    Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);

}
