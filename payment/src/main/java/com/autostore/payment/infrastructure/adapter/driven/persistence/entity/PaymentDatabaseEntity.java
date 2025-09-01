package com.autostore.payment.infrastructure.adapter.driven.persistence.entity;


import com.autostore.payment.domain.Payment;
import com.autostore.payment.domain.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class PaymentDatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private String transactionId;

    @Column(nullable = false)
    private Integer totalItems;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public static PaymentDatabaseEntity fromDomain(Payment payment) {
        return PaymentDatabaseEntity.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .transactionId(payment.getTransactionId())
                .totalItems(payment.getTotalItems())
                .totalAmount(payment.getTotalAmount())
                .status(payment.getStatus())
                .createdAt(payment.getCreatedAt())
                .updatedAt(payment.getUpdatedAt())
                .build();
    }

    @PrePersist
    public void prePersist() {
        var now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
        status = PaymentStatus.PENDING;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Payment toDomain() {
        return Payment.builder()
                .id(id)
                .orderId(orderId)
                .transactionId(transactionId)
                .totalItems(totalItems)
                .totalAmount(totalAmount)
                .status(status)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

}
