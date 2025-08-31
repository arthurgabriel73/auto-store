package com.autostore.payment.domain.event;


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
