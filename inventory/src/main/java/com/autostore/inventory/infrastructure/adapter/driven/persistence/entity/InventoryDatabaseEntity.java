package com.autostore.inventory.infrastructure.adapter.driven.persistence.entity;


import com.autostore.inventory.domain.Inventory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventory")
public class InventoryDatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productCode;

    @Column(nullable = false)
    private Integer availableQuantity;


    public static InventoryDatabaseEntity fromDomain(Inventory inventory) {
        return InventoryDatabaseEntity.builder()
                .id(inventory.getId())
                .productCode(inventory.getProductCode())
                .availableQuantity(inventory.getAvailableQuantity())
                .build();
    }

    public Inventory toDomain() {
        return Inventory.builder()
                .id(this.id)
                .productCode(this.productCode)
                .availableQuantity(this.availableQuantity)
                .build();
    }

}
