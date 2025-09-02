package com.autostore.bff.application.service;


import com.autostore.bff.application.port.driven.EventProducer;
import com.autostore.bff.application.port.driven.EventRepository;
import com.autostore.bff.application.port.driver.OrderOrchestratorServiceDriverPort;
import com.autostore.bff.domain.DomainEvent;
import com.autostore.bff.domain.Topic;
import com.autostore.bff.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@AllArgsConstructor
public class OrderOrchestratorService implements OrderOrchestratorServiceDriverPort {

    private final SagaExecutionController sagaExecutionController;
    private final EventRepository<Order> eventRepository;
    private final EventProducer<Order> eventProducer;

    @Override
    public void consumeOrderSagaEvent(DomainEvent<Order> event, Topic topic) {
        log.info("Consuming saga event with id: {} on topic: {}", event.getId(), topic);
        eventRepository.save(event);
        var nextTopic = sagaExecutionController.getNextTopic(topic);
        if (nextTopic != null) eventProducer.sendEvent(event, nextTopic.getTopic());
    }

}
