package com.autostore.validation.infrastructure.adapter.driven.persistence.entity;


import com.autostore.validation.domain.ValidationProduct;
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
@Table(name = "validation_products")
public class ValidationProductDatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    public static ValidationProductDatabaseEntity fromDomain(ValidationProduct validationProduct) {
        return ValidationProductDatabaseEntity.builder()
                .id(validationProduct.id())
                .code(validationProduct.code())
                .build();
    }

    public ValidationProduct toDomain() {
        return ValidationProduct.builder()
                .id(this.id)
                .code(this.code)
                .build();
    }

}
