package com.autostore.order.infrastructure.adapter.driven.event;


import com.autostore.order.application.port.driven.EventProducer;
import com.autostore.order.domain.DomainEvent;
import com.autostore.order.domain.Order;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;


@Getter
@Slf4j
public class FakeEventProducer implements EventProducer {

    Map<String, DomainEvent<?>> events = new HashMap<>();

    @Override
    public void sendEvent(DomainEvent<Order> event, String topic) {
        events.put(topic, event);
        log.info("Sending event to topic {}: {}", topic, event);
    }

    public void clearEvents() {
        events.clear();
    }

}
