package com.autostore.order.infrastructure.adapter.driven.persistence;


import com.autostore.order.application.port.driven.OrderRepository;
import com.autostore.order.domain.Order;
import com.autostore.order.infrastructure.adapter.driven.persistence.repository.OrderMongoRepository;
import com.autostore.order.infrastructure.adapter.driven.persistence.schema.OrderSchema;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Named
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderMongoRepository repository;

    @Override
    public Order save(Order order) {
        return repository.save(OrderSchema.fromDomain(order)).toDomain();
    }

    @Override
    public List<Order> findAllByIds(List<String> orderIds) {
        return repository.findAllById(orderIds).stream().map(OrderSchema::toDomain).toList();
    }

}
