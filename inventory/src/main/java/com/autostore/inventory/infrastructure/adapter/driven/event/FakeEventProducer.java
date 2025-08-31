package com.autostore.inventory.infrastructure.adapter.driven.event;


import com.autostore.inventory.application.port.driven.EventProducer;
import com.autostore.inventory.domain.event.DomainEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


@Getter
@Slf4j
public class FakeEventProducer implements EventProducer {

    List<DomainEvent<?>> events = new ArrayList<>();

    @Override
    public void sendEvent(DomainEvent<?> event, String topic) {
        events.add(event);
        log.info("Sending event to topic {}: {}", topic, event);
    }

    public void clearEvents() {
        events.clear();
    }

}
