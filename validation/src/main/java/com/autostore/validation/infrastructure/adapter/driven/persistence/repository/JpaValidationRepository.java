package com.autostore.validation.infrastructure.adapter.driven.persistence.repository;


import com.autostore.validation.infrastructure.adapter.driven.persistence.entity.ValidationDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface JpaValidationRepository extends JpaRepository<ValidationDatabaseEntity, Long> {

    Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);

    Optional<ValidationDatabaseEntity> findByOrderIdAndTransactionId(String orderId, String transactionId);

}
