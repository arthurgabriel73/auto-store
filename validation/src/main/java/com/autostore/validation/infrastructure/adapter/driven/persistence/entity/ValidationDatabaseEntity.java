package com.autostore.validation.infrastructure.adapter.driven.persistence.entity;


import com.autostore.validation.domain.Validation;
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
@Table(name = "validation")
public class ValidationDatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private String transactionId;

    @Column(nullable = false)
    private boolean success;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public static ValidationDatabaseEntity fromDomain(Validation validation) {
        return ValidationDatabaseEntity.builder()
                .id(validation.getId())
                .orderId(validation.getOrderId())
                .transactionId(validation.getTransactionId())
                .success(validation.isSuccess())
                .createdAt(validation.getCreatedAt())
                .updatedAt(validation.getUpdatedAt())
                .build();
    }

    @PrePersist
    public void prePersist() {
        var now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Validation toDomain() {
        return Validation.builder()
                .id(id)
                .orderId(orderId)
                .transactionId(transactionId)
                .success(success)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

}
