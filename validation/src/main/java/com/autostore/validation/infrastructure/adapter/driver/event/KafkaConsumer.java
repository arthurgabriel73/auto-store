package com.autostore.validation.infrastructure.adapter.driver.event;


import com.autostore.validation.application.port.driver.ValidateProductsDriverPort;
import com.autostore.validation.application.port.driver.model.command.ValidateProductsCommand;
import com.autostore.validation.application.port.event.Order;
import com.autostore.validation.domain.DomainEvent;
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
        DomainEvent<Order> event = jsonUtil.fromJson(payload, DomainEvent.class);
        validateProductsDriverPort.execute(new ValidateProductsCommand(event));
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.command-topics.rollback-order-validation-command}"
    )
    public void consumeRollbackOrderValidationCommand(String payload) {
        log.info("Received rollback event {}", payload);
        DomainEvent<Order> event = jsonUtil.fromJson(payload, DomainEvent.class);
        validateProductsDriverPort.rollback(event);
    }

}
