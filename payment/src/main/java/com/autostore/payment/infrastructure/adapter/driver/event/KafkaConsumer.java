package com.autostore.payment.infrastructure.adapter.driver.event;


import com.autostore.payment.application.port.driver.ProcessPaymentDriverPort;
import com.autostore.payment.application.port.driver.model.command.ProcessPaymentCommand;
import com.autostore.payment.application.port.event.Order;
import com.autostore.payment.domain.DomainEvent;
import com.autostore.payment.infrastructure.adapter.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class KafkaConsumer {

    private final JsonUtil jsonUtil;
    private final ProcessPaymentDriverPort processPaymentDriverPort;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.command-topics.process-payment-command}"
    )
    public void consumeProcessPaymentCommand(String payload) {
        log.info("Receiving event {}", payload);
        DomainEvent<Order> event = jsonUtil.fromJson(payload, DomainEvent.class);
        processPaymentDriverPort.execute(new ProcessPaymentCommand(event));
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.command-topics.refund-payment-command}"
    )
    public void consumeRefundPaymentCommand(String payload) {
        log.info("Received rollback event {}", payload);
        DomainEvent<Order> event = jsonUtil.fromJson(payload, DomainEvent.class);
        processPaymentDriverPort.rollback(event);
    }

}
