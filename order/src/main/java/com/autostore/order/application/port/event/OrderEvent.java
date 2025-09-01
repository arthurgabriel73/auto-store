package com.autostore.order.application.port.event;


import com.autostore.order.domain.DomainEvent;
import com.autostore.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent extends DomainEvent<Order> {

    protected String orderId;
    protected String transactionId;

}
