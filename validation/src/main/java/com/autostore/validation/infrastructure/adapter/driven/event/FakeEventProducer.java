package com.autostore.validation.infrastructure.adapter.driven.event;


import com.autostore.validation.application.port.driven.EventProducer;
import com.autostore.validation.domain.event.DomainEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;


@Getter
@Slf4j
public class FakeEventProducer implements EventProducer {

    Map<String, DomainEvent<?>> events = new HashMap<>();

    @Override
    public void sendEvent(DomainEvent<?> event, String topic) {
        events.put(topic, event);
        log.info("Sending event to topic {}: {}", topic, event);
    }

    public void clearEvents() {
        events.clear();
    }

}
