package com.autostore.inventory.application.port.driven;


import com.autostore.inventory.domain.Product;


public interface ProductRepository {

    Product save(Product product);
    
    Boolean existsByCode(String code);

}
