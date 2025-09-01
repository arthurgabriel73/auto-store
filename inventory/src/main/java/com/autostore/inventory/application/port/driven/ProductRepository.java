package com.autostore.inventory.application.port.driven;


import com.autostore.inventory.domain.Product;

import java.util.Optional;


public interface ProductRepository {

    Product save(Product product);

    Boolean existsByCode(String code);

    Optional<Product> findByCode(String code);

}
