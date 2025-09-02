package com.autostore.order.application.port.driver.model.query;


import java.util.List;


public record ListOrdersByIdsQuery(List<String> orderIds) {

}
