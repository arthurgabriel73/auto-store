package com.autostore.validation.infrastructure.adapter.driven.persistence.repository;


import com.autostore.validation.infrastructure.adapter.driven.persistence.entity.ValidationProductDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaValidationProductRepository extends JpaRepository<ValidationProductDatabaseEntity, Long> {

    Boolean existsByCode(String code);

}
