package com.autostore.order.infrastructure.adapter.driven.persistence;


import com.autostore.order.application.port.driven.OrderRepository;
import com.autostore.order.domain.Order;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;


public class InMemoryOrderRepository implements OrderRepository {

    private final Faker faker = new Faker();

    List<Order> orders = new ArrayList<>();

    @Override
    public Order save(Order order) {
        Order toSave = toNewInstance(order);
        orders.removeIf(o -> isSameOrder(o, toSave));
        orders.add(toSave);
        return toNewInstance(toSave);
    }

    @Override
    public List<Order> findAllByIds(List<String> orderIds) {
        return List.of();
    }

    private Order toNewInstance(Order order) {
        return Order.of(
                order.getId() != null ? order.getId() : faker.idNumber().valid(),
                order.getProducts(),
                order.getCreatedAt(),
                order.getTransactionId(),
                order.getTotalAmount(),
                order.getTotalItems(),
                order.getCustomer()
        );
    }

    private boolean isSameOrder(Order a, Order b) {
        return a.getId().equals(b.getId());
    }

}
