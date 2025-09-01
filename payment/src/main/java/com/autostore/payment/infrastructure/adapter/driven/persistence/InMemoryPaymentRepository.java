package com.autostore.payment.infrastructure.adapter.driven.persistence;


import com.autostore.payment.application.port.driven.PaymentRepository;
import com.autostore.payment.domain.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class InMemoryPaymentRepository implements PaymentRepository {

    List<Payment> payments = new ArrayList<>();

    @Override
    public Payment save(Payment payment) {
        var newPayment = toNewInstance(payment);
        payments.add(newPayment);
        return toNewInstance(newPayment);
    }

    @Override
    public Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId) {
        return payments.stream()
                .anyMatch(p -> p.getOrderId().equals(orderId) && p.getTransactionId().equals(transactionId));
    }

    @Override
    public Optional<Payment> findByOrderIdAndTransactionId(String orderId, String transactionId) {
        return payments.stream()
                .filter(p -> p.getOrderId().equals(orderId) && p.getTransactionId().equals(transactionId))
                .findFirst()
                .map(this::toNewInstance);
    }

    private Payment toNewInstance(Payment payment) {
        return Payment
                .builder()
                .id(payment.getId() != null ? payment.getId() : payments.size() + 1)
                .orderId(payment.getOrderId())
                .transactionId(payment.getTransactionId())
                .totalItems(payment.getTotalItems())
                .totalAmount(payment.getTotalAmount())
                .createdAt(payment.getCreatedAt())
                .updatedAt(payment.getUpdatedAt())
                .status(payment.getStatus())
                .build();
    }

}
