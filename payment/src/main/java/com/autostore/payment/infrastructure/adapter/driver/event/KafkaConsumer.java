package com.autostore.payment.infrastructure.adapter.driver.event;


import com.autostore.payment.application.port.driver.ProcessPaymentDriverPort;
import com.autostore.payment.application.port.driver.model.command.ProcessPaymentCommand;
import com.autostore.payment.application.port.event.OrderEvent;
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
            topics = "${spring.kafka.command-topics.process-payment}"
    )
    public void consumeProcessPaymentCommand(String payload) {
        log.info("Receiving event {}", payload);
        OrderEvent event = jsonUtil.fromJson(jsonUtil.toJson(payload), OrderEvent.class);
        processPaymentDriverPort.execute(new ProcessPaymentCommand(event));
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.command-topics.refund-payment}"
    )
    public void consumeRefundPaymentCommand(String payload) {
        log.info("Received rollback event {}", payload);
        OrderEvent event = jsonUtil.fromJson(jsonUtil.toJson(payload), OrderEvent.class);
        processPaymentDriverPort.rollback(event);
    }

}
