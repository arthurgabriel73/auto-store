package com.autostore.payment.infrastructure.adapter.driven.persistence.repository;


import com.autostore.payment.infrastructure.adapter.driven.persistence.entity.PaymentDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface JpaPaymentRepository extends JpaRepository<PaymentDatabaseEntity, Long> {

    Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);

    Optional<PaymentDatabaseEntity> findByOrderIdAndTransactionId(String orderId, String transactionId);

}
