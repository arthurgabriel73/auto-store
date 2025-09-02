package com.autostore.inventory.infrastructure.adapter.driver.event;


import com.autostore.inventory.application.port.driver.UpdateInventoryDriverPort;
import com.autostore.inventory.application.port.driver.model.command.UpdateInventoryCommand;
import com.autostore.inventory.application.port.event.Order;
import com.autostore.inventory.domain.DomainEvent;
import com.autostore.inventory.infrastructure.adapter.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class KafkaConsumer {

    private final JsonUtil jsonUtil;
    private final UpdateInventoryDriverPort updateInventoryDriverPort;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.command-topics.update-inventory-command}"
    )
    public void consumeUpdateInventoryCommand(String payload) {
        log.info("Receiving event {}", payload);
        DomainEvent<Order> event = jsonUtil.fromJson(payload, new TypeReference<DomainEvent<Order>>() {
        });
        updateInventoryDriverPort.execute(new UpdateInventoryCommand(event));
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.command-topics.rollback-inventory-update-command}"
    )
    public void consumeRollbackInventoryUpdateCommand(String payload) {
        log.info("Received rollback event {}", payload);
        DomainEvent<Order> event = jsonUtil.fromJson(payload, new TypeReference<DomainEvent<Order>>() {
        });
        updateInventoryDriverPort.rollback(event);
    }

}
