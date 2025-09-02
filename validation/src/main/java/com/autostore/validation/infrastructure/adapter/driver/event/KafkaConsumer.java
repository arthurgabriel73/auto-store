package com.autostore.validation.infrastructure.adapter.driver.event;


import com.autostore.validation.application.port.driver.ValidateProductsDriverPort;
import com.autostore.validation.application.port.driver.model.command.ValidateProductsCommand;
import com.autostore.validation.application.port.event.OrderEvent;
import com.autostore.validation.infrastructure.adapter.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class KafkaConsumer {

    private final JsonUtil jsonUtil;
    private final ValidateProductsDriverPort validateProductsDriverPort;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.command-topics.validate-order-command}"
    )
    public void consumeValidateOrderCommand(String payload) {
        log.info("Receiving event {}", payload);
        OrderEvent event = jsonUtil.fromJson(jsonUtil.toJson(payload), OrderEvent.class);
        validateProductsDriverPort.execute(new ValidateProductsCommand(event));
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.command-topics.rollback-order-validation-command}"
    )
    public void consumeRollbackOrderValidationCommand(String payload) {
        log.info("Received rollback event {}", payload);
        OrderEvent event = jsonUtil.fromJson(jsonUtil.toJson(payload), OrderEvent.class);
        validateProductsDriverPort.rollback(event);
    }

}
