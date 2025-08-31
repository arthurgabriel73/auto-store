package com.autostore.inventory.application.port.driven;


import com.autostore.inventory.domain.event.DomainEvent;


public interface EventProducer {

    void sendEvent(DomainEvent<?> event, String topic);

}
