package com.autostore.inventory.domain.event;


import lombok.Builder;


@Builder
public class OrderEvent extends DomainEvent<Order> {

    protected final String orderId;
    protected final String transactionId;

}
