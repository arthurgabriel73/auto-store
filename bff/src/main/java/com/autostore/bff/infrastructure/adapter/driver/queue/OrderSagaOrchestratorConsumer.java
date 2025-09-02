package com.autostore.bff.infrastructure.adapter.driver.queue;


import com.autostore.bff.application.port.driver.OrderOrchestratorServiceDriverPort;
import com.autostore.bff.domain.DomainEvent;
import com.autostore.bff.domain.Topic;
import com.autostore.bff.domain.order.Order;
import com.autostore.bff.infrastructure.adapter.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class OrderSagaOrchestratorConsumer {

    private final OrderOrchestratorServiceDriverPort orderOrchestratorServiceDriverPort;
    private final JsonUtil jsonUtil;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "#{@orderSagaTopics}"
    )
    public void consumeOrderSagaEvent(String payload, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("Receiving event from topic {}: {}", topic, payload);
        DomainEvent<Order> event = jsonUtil.fromJson(payload, new TypeReference<DomainEvent<Order>>() {
        });
        orderOrchestratorServiceDriverPort.consumeOrderSagaEvent(event, Topic.fromString(topic));
    }

}
