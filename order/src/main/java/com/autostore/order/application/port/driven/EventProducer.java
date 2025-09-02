package com.autostore.order.application.port.driven;


import com.autostore.order.domain.DomainEvent;
import com.autostore.order.domain.Order;


public interface EventProducer {

    void sendEvent(DomainEvent<Order> event, String topic);

}
