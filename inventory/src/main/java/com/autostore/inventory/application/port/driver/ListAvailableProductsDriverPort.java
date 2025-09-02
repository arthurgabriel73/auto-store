package com.autostore.inventory.application.port.driver;


import com.autostore.inventory.application.port.driver.model.query.ListAvailableProductsQueryOutput;


public interface ListAvailableProductsDriverPort {

    ListAvailableProductsQueryOutput query();

}
