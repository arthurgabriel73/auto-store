package com.autostore.order.application.port.driven;


import com.autostore.order.domain.Order;


public interface OrderRepository {

    Order save(Order order);

}
