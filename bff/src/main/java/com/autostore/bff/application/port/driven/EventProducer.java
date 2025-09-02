package com.autostore.bff.application.port.driven;


import com.autostore.bff.domain.DomainEvent;


public interface EventProducer {

    void sendEvent(DomainEvent<?> event, String topic);

}
