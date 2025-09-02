package com.autostore.bff.infrastructure.adapter.driven.event;


import com.autostore.bff.application.port.driven.EventProducer;
import com.autostore.bff.domain.DomainEvent;
import com.autostore.bff.infrastructure.adapter.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class SagaOrchestratorProducer implements EventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final JsonUtil jsonUtil;


    public void sendEvent(DomainEvent<?> event, String topic) {
        String payload = jsonUtil.toJson(event);
        try {
            log.info("Sending event to topic {} with data: {}", topic, payload);
            kafkaTemplate.send(topic, payload);
        } catch (Exception ex) {
            log.error("Error sending event to Kafka topic {} with data: {}", topic, payload, ex);
        }
    }

}
