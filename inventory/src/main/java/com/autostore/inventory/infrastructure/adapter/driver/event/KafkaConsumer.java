package com.autostore.inventory.infrastructure.adapter.driver.event;


import com.autostore.inventory.application.port.driver.UpdateInventoryDriverPort;
import com.autostore.inventory.application.port.driver.model.command.UpdateInventoryCommand;
import com.autostore.inventory.application.port.event.OrderEvent;
import com.autostore.inventory.infrastructure.adapter.util.JsonUtil;
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
            topics = "${spring.kafka.command-topics.update-inventory}"
    )
    public void consumeUpdateInventoryCommand(String payload) {
        log.info("Receiving event {}", payload);
        OrderEvent event = jsonUtil.fromJson(jsonUtil.toJson(payload), OrderEvent.class);
        updateInventoryDriverPort.execute(new UpdateInventoryCommand(event));
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.command-topics.rollback-inventory-update}"
    )
    public void consumeRollbackInventoryUpdateCommand(String payload) {
        log.info("Received rollback event {}", payload);
        OrderEvent event = jsonUtil.fromJson(jsonUtil.toJson(payload), OrderEvent.class);
        updateInventoryDriverPort.rollback(event);
    }

}
