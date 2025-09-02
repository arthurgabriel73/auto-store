package com.autostore.bff.application.service;


import com.autostore.bff.application.port.driven.EventProducer;
import com.autostore.bff.application.port.driver.OrchestratorServiceDriverPort;
import com.autostore.bff.domain.DomainEvent;
import com.autostore.bff.domain.Topic;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@AllArgsConstructor
public class OrchestratorService implements OrchestratorServiceDriverPort {

    private final SagaExecutionController sagaExecutionController;
    private final EventProducer eventProducer;

    @Override
    public void consumeSagaEvent(DomainEvent<?> event, Topic topic) {
        log.info("Consuming saga event with id: {} on topic: {}", event.getId(), topic);
        var nextTopic = sagaExecutionController.getNextTopic(topic);
        if (nextTopic != null) eventProducer.sendEvent(event, nextTopic.getTopic());
    }

}
