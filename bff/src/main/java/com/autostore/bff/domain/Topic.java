package com.autostore.bff.domain;


import lombok.Getter;

import java.util.Arrays;


@Getter
public enum Topic {
    // PRIMARY COMMANDS
    VALIDATE_ORDER_COMMAND("validate-order-command"),
    PROCESS_PAYMENT_COMMAND("process-payment-command"),
    UPDATE_INVENTORY_COMMAND("update-inventory-command"),

    // ROLLBACK COMMANDS
    ROLLBACK_INVENTORY_UPDATE_COMMAND("rollback-inventory-update-command"),
    REFUND_PAYMENT_COMMAND("refund-payment-command"),
    ROLLBACK_ORDER_VALIDATION_COMMAND("rollback-order-validation-command"),

    // TOPIC SUCCESS
    ORDER_SERVICE_ORDER_CREATED_V1("order-service-order-created-v1"),
    VALIDATION_SERVICE_VALIDATION_UPDATED_V1("validation-service-validation-updated-v1"),
    PAYMENT_SERVICE_PAYMENT_PROCESSED_V1("payment-service-payment-processed-v1"),
    INVENTORY_SERVICE_INVENTORY_UPDATED_V1("inventory-service-inventory-updated-v1"),

    // TOPIC FAILED
    ORDER_SERVICE_ORDER_FAILED_V1("order-service-order-failed-v1"),
    VALIDATION_SERVICE_VALIDATION_FAILED_V1("validation-service-validation-failed-v1"),
    PAYMENT_SERVICE_PAYMENT_FAILED_V1("payment-service-payment-failed-v1"),
    INVENTORY_SERVICE_INVENTORY_FAILED_V1("inventory-service-inventory-failed-v1"),

    // ROLLBACK SUCCESS
    INVENTORY_SERVICE_INVENTORY_ROLLBACK_SUCCESS_V1("inventory-service-inventory-rollback-success-v1"),
    PAYMENT_SERVICE_PAYMENT_REFUND_SUCCESS_V1("payment-service-payment-refund-success-v1"),
    VALIDATION_SERVICE_VALIDATION_ROLLBACK_SUCCESS_V1("validation-service-validation-rollback-success-v1"),

    // ROLLBACK FAILED
    INVENTORY_SERVICE_INVENTORY_ROLLBACK_FAILED_V1("inventory-service-inventory-rollback-failed-v1"),
    PAYMENT_SERVICE_PAYMENT_REFUND_FAILED_V1("payment-service-payment-refund-failed-v1"),
    VALIDATION_SERVICE_VALIDATION_ROLLBACK_FAILED_V1("validation-service-validation-rollback-failed-v1"),
    ;

    private final String topic;

    Topic(String s) {
        this.topic = s;
    }

    public static String[] getAllTopicNames() {
        return Arrays.stream(values())
                .map(Topic::getTopic)
                .toArray(String[]::new);
    }

    public static Topic fromString(String topicName) {
        return Arrays.stream(values())
                .filter(t -> t.getTopic().equals(topicName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No topic found with name: " + topicName));
    }
}
