package com.autostore.bff.infrastructure.config.kafka;


import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

import static com.autostore.bff.domain.Topic.*;


@EnableKafka
@Configuration
public class KafkaConfig {

    private static final Integer PARTITION_COUNT = 1;
    private static final Integer REPLICA_COUNT = 1;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProps());
    }

    private Map<String, Object> consumerProps() {
        var props = new HashMap<String, Object>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        return props;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerProps());
    }

    private Map<String, Object> producerProps() {
        var props = new HashMap<String, Object>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    private NewTopic buildTopic(String name) {
        return TopicBuilder
                .name(name)
                .replicas(REPLICA_COUNT)
                .partitions(PARTITION_COUNT)
                .build();
    }

    @Bean
    public NewTopic validateOrderCommandTopic() {
        return buildTopic(VALIDATE_ORDER_COMMAND.getTopic());
    }

    @Bean
    public NewTopic rollbackOrderValidationCommandTopic() {
        return buildTopic(ROLLBACK_ORDER_VALIDATION_COMMAND.getTopic());
    }

    @Bean
    public NewTopic processPaymentCommandTopic() {
        return buildTopic(PROCESS_PAYMENT_COMMAND.getTopic());
    }

    @Bean
    public NewTopic refundPaymentCommandTopic() {
        return buildTopic(REFUND_PAYMENT_COMMAND.getTopic());
    }

    @Bean
    public NewTopic updateInventoryCommandTopic() {
        return buildTopic(UPDATE_INVENTORY_COMMAND.getTopic());
    }

    @Bean
    public NewTopic rollbackInventoryUpdateCommandTopic() {
        return buildTopic(ROLLBACK_INVENTORY_UPDATE_COMMAND.getTopic());
    }

    @Bean
    public NewTopic orderServiceOrderCreatedV1Topic() {
        return buildTopic(ORDER_SERVICE_ORDER_CREATED_V1.getTopic());
    }

    @Bean
    public NewTopic orderServiceOrderFailedV1Topic() {
        return buildTopic(ORDER_SERVICE_ORDER_FAILED_V1.getTopic());
    }

    @Bean
    public NewTopic inventoryServiceInventoryUpdatedV1Topic() {
        return buildTopic(INVENTORY_SERVICE_INVENTORY_UPDATED_V1.getTopic());
    }

    @Bean
    public NewTopic inventoryServiceInventoryFailedV1Topic() {
        return buildTopic(INVENTORY_SERVICE_INVENTORY_FAILED_V1.getTopic());
    }

    @Bean
    public NewTopic inventoryServiceInventoryRollbackSuccessV1Topic() {
        return buildTopic(INVENTORY_SERVICE_INVENTORY_ROLLBACK_SUCCESS_V1.getTopic());
    }

    @Bean
    public NewTopic inventoryServiceInventoryRollbackFailedV1Topic() {
        return buildTopic(INVENTORY_SERVICE_INVENTORY_ROLLBACK_FAILED_V1.getTopic());
    }

    @Bean
    public NewTopic paymentServicePaymentProcessedV1Topic() {
        return buildTopic(PAYMENT_SERVICE_PAYMENT_PROCESSED_V1.getTopic());
    }

    @Bean
    public NewTopic paymentServicePaymentFailedV1Topic() {
        return buildTopic(PAYMENT_SERVICE_PAYMENT_FAILED_V1.getTopic());
    }

    @Bean
    public NewTopic paymentServicePaymentRefundSuccessV1Topic() {
        return buildTopic(PAYMENT_SERVICE_PAYMENT_REFUND_SUCCESS_V1.getTopic());
    }

    @Bean
    public NewTopic paymentServicePaymentRefundFailedV1Topic() {
        return buildTopic(PAYMENT_SERVICE_PAYMENT_REFUND_FAILED_V1.getTopic());
    }

    @Bean
    public NewTopic validationServiceValidationUpdatedV1Topic() {
        return buildTopic(VALIDATION_SERVICE_VALIDATION_UPDATED_V1.getTopic());
    }

    @Bean
    public NewTopic validationServiceValidationFailedV1Topic() {
        return buildTopic(VALIDATION_SERVICE_VALIDATION_FAILED_V1.getTopic());
    }

    @Bean
    public NewTopic validationServiceValidationRollbackSuccessV1Topic() {
        return buildTopic(VALIDATION_SERVICE_VALIDATION_ROLLBACK_SUCCESS_V1.getTopic());
    }

    @Bean
    public NewTopic validationServiceValidationRollbackFailedV1Topic() {
        return buildTopic(VALIDATION_SERVICE_VALIDATION_ROLLBACK_FAILED_V1.getTopic());
    }

}
