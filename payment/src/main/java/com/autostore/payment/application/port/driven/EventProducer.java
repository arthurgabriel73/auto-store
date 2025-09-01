package com.autostore.payment.application.port.driven;


import com.autostore.payment.domain.DomainEvent;


public interface EventProducer {

    void sendEvent(DomainEvent<?> event, String topic);

}
