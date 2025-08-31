package com.autostore.validation.application.port.driven;


import com.autostore.validation.domain.ValidationProduct;


public interface ValidationProductRepository {

    ValidationProduct save(ValidationProduct validationProduct);

    Boolean existsByCode(String code);

}
