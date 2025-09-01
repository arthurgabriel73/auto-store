package com.autostore.inventory.infrastructure.adapter.driven.persistence.entity;


import com.autostore.inventory.domain.Activity;
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
@Table(name = "activities")
public class ActivityDatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private InventoryDatabaseEntity inventory;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private String transactionId;

    @Column(nullable = false)
    private Integer orderQuantity;

    @Column(nullable = false)
    private Integer oldQuantity;

    @Column(nullable = false)
    private Integer newQuantity;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public static ActivityDatabaseEntity fromDomain(Activity activity) {
        return ActivityDatabaseEntity.builder()
                .id(activity.id())
                .inventory(InventoryDatabaseEntity.fromDomain(activity.inventory()))
                .orderId(activity.orderId())
                .transactionId(activity.transactionId())
                .orderQuantity(activity.orderQuantity())
                .oldQuantity(activity.oldQuantity())
                .newQuantity(activity.newQuantity())
                .createdAt(activity.createdAt())
                .updatedAt(activity.updatedAt())
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

    public Activity toDomain() {
        return Activity.builder()
                .id(this.id)
                .inventory(this.inventory.toDomain())
                .orderId(this.orderId)
                .transactionId(this.transactionId)
                .orderQuantity(this.orderQuantity)
                .oldQuantity(this.oldQuantity)
                .newQuantity(this.newQuantity)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

}
