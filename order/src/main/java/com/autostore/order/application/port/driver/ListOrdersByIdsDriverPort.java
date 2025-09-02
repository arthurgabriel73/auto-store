package com.autostore.order.application.port.driver;


import com.autostore.order.application.port.driver.model.query.ListOrdersByIdsQuery;
import com.autostore.order.application.port.driver.model.query.ListOrdersByIdsQueryOutput;


public interface ListOrdersByIdsDriverPort {

    ListOrdersByIdsQueryOutput execute(ListOrdersByIdsQuery query);

}
