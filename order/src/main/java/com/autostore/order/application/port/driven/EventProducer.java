package com.autostore.order.application.port.driven;


import com.autostore.order.domain.DomainEvent;


public interface EventProducer {

    void sendEvent(DomainEvent<?> event, String topic);

}
