package com.autostore.payment.application.port.event;


import com.autostore.payment.domain.DomainEvent;
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
