package com.autostore.inventory.infrastructure.adapter.driven.persistence.entity;


import com.autostore.inventory.domain.Product;
import com.autostore.inventory.domain.ProductCategory;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Map;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class ProductDatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Double unitValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductCategory category;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> details;

    public static ProductDatabaseEntity fromDomain(Product product) {
        return ProductDatabaseEntity.builder()
                .id(product.getId())
                .code(product.getCode())
                .unitValue(product.getUnitValue())
                .category(product.getCategory())
                .details(product.getDetails())
                .build();
    }

    public Product toDomain() {
        return Product.builder()
                .id(this.id)
                .code(this.code)
                .unitValue(this.unitValue)
                .category(this.category)
                .details(this.details)
                .build();
    }

}
