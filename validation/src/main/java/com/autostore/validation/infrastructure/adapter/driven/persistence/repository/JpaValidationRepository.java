package com.autostore.validation.infrastructure.adapter.driven.persistence.repository;


import com.autostore.validation.infrastructure.adapter.driven.persistence.entity.ValidationDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface JpaValidationRepository extends JpaRepository<ValidationDatabaseEntity, Long> {

    Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);

    Optional<ValidationDatabaseEntity> findByOrderIdAndTransactionId(String orderId, String transactionId);

    @Query("SELECT v.orderId FROM ValidationDatabaseEntity v WHERE v.success = :success")
    List<String> findBySuccess(boolean success);

}
