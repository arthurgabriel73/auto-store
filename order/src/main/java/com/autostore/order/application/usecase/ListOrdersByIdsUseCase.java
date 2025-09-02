package com.autostore.order.application.usecase;


import com.autostore.order.application.port.driven.OrderRepository;
import com.autostore.order.application.port.driver.ListOrdersByIdsDriverPort;
import com.autostore.order.application.port.driver.model.query.ListOrdersByIdsQuery;
import com.autostore.order.application.port.driver.model.query.ListOrdersByIdsQueryOutput;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ListOrdersByIdsUseCase implements ListOrdersByIdsDriverPort {

    private final OrderRepository orderRepository;

    @Override
    public ListOrdersByIdsQueryOutput execute(ListOrdersByIdsQuery query) {
        var orders = orderRepository.findAllByIds(query.orderIds());
        return new ListOrdersByIdsQueryOutput(orders);
    }

}
