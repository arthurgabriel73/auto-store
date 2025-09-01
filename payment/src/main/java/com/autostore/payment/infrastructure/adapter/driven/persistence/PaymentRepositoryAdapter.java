package com.autostore.payment.infrastructure.adapter.driven.persistence;


import com.autostore.payment.application.port.driven.PaymentRepository;
import com.autostore.payment.domain.Payment;
import com.autostore.payment.infrastructure.adapter.driven.persistence.entity.PaymentDatabaseEntity;
import com.autostore.payment.infrastructure.adapter.driven.persistence.repository.JpaPaymentRepository;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@Named
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepository {

    private final JpaPaymentRepository repository;

    @Override
    public Payment save(Payment payment) {
        return repository.save(PaymentDatabaseEntity.fromDomain(payment)).toDomain();
    }

    @Override
    public Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId) {
        return repository.existsByOrderIdAndTransactionId(orderId, transactionId);
    }

    @Override
    public Optional<Payment> findByOrderIdAndTransactionId(String orderId, String transactionId) {
        var payment = repository.findByOrderIdAndTransactionId(orderId, transactionId);
        if (payment.isEmpty()) return Optional.empty();
        return payment.map(PaymentDatabaseEntity::toDomain);
    }

}
