package com.autostore.inventory.application.port.driver.model.query;


import com.autostore.inventory.domain.Product;

import java.util.List;


public record ListAvailableProductsQueryOutput(List<Product> products) {

}
