package com.autostore.bff.application.port.driver;


import com.autostore.bff.domain.DomainEvent;
import com.autostore.bff.domain.Topic;
import com.autostore.bff.domain.order.Order;


public interface OrderOrchestratorServiceDriverPort {

    void consumeOrderSagaEvent(DomainEvent<Order> event, Topic topic);

}
