package com.autostore.payment.application.port.event;


import lombok.Getter;


@Getter
public enum Topic {
    PAYMENT_SERVICE_PAYMENT_UPDATED_V1("payment-service-payment-updated-v1"),
    PAYMENT_SERVICE_PAYMENT_FAILED_V1("payment-service-payment-failed-v1"),
    PAYMENT_SERVICE_PAYMENT_ROLLBACK_SUCCESS_V1("payment-service-payment-rollback-success-v1"),
    PAYMENT_SERVICE_PAYMENT_ROLLBACK_FAILED_V1("payment-service-payment-rollback-failed-v1");


    private final String topic;

    Topic(String s) {
        this.topic = s;
    }
}
