package com.autostore.bff.application.service;


import com.autostore.bff.application.port.driven.EventProducer;
import com.autostore.bff.application.port.driven.EventRepository;
import com.autostore.bff.application.port.driver.OrderOrchestratorServiceDriverPort;
import com.autostore.bff.domain.DomainEvent;
import com.autostore.bff.domain.History;
import com.autostore.bff.domain.Topic;
import com.autostore.bff.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;


@Slf4j
@AllArgsConstructor
public class OrderOrchestratorService implements OrderOrchestratorServiceDriverPort {

    private final SagaExecutionController sagaExecutionController;
    private final EventRepository<Order> eventRepository;
    private final EventProducer<Order> eventProducer;

    @Override
    public void consumeOrderSagaEvent(DomainEvent<Order> event, Topic topic) {
        log.info("Consuming saga event with id: {} on topic: {}", event.getId(), topic);
        addEventHistory(event, topic);
        var nextTopic = sagaExecutionController.getNextTopic(topic);
        if (nextTopic != null) eventProducer.sendEvent(event, nextTopic.getTopic());
    }

    private void addEventHistory(DomainEvent<Order> event, Topic topic) {
        var existingEvent = eventRepository.findTop1ByTransactionIdOrderByCreatedAtDesc(event.getTransactionId());
        existingEvent.ifPresent(orderDomainEvent -> orderDomainEvent.getEventHistory().forEach(event::addToHistory));
        event.addToHistory(History.builder()
                .topic(topic.getTopic())
                .createdAt(LocalDateTime.now())
                .build());
        eventRepository.save(event);
    }

}
