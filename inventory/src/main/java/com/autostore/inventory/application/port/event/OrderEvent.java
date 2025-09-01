package com.autostore.inventory.application.port.event;


import com.autostore.inventory.domain.DomainEvent;
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
