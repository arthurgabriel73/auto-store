package com.autostore.order.application.port.driven;


import com.autostore.order.domain.Order;

import java.util.List;


public interface OrderRepository {

    Order save(Order order);

    List<Order> findAllByIds(List<String> orderIds);

}
