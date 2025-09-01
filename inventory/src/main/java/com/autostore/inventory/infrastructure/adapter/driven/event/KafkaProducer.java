package com.autostore.inventory.infrastructure.adapter.driven.event;


import com.autostore.inventory.application.port.driven.EventProducer;
import com.autostore.inventory.domain.DomainEvent;
import com.autostore.inventory.infrastructure.adapter.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer implements EventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final JsonUtil jsonUtil;


    @Override
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
