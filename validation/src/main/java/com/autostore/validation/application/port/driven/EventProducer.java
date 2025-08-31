package com.autostore.validation.application.port.driven;


import com.autostore.validation.domain.event.DomainEvent;


public interface EventProducer {

    void sendEvent(DomainEvent<?> event, String topic);

}
