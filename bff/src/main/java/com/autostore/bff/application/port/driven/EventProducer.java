package com.autostore.bff.application.port.driven;


import com.autostore.bff.domain.DomainEvent;


public interface EventProducer<T> {

    void sendEvent(DomainEvent<T> event, String topic);

}
