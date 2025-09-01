package com.autostore.payment.application.port.event;


import lombok.Getter;


@Getter
public enum Topic {
    PAYMENT_SERVICE_PAYMENT_PROCESSED_V1("payment-service-payment-processed-v1"),
    PAYMENT_SERVICE_PAYMENT_FAILED_V1("payment-service-payment-failed-v1"),
    PAYMENT_SERVICE_PAYMENT_REFUND_SUCCESS_V1("payment-service-payment-refund-success-v1"),
    PAYMENT_SERVICE_PAYMENT_REFUND_FAILED_V1("payment-service-payment-refund-failed-v1");


    private final String topic;

    Topic(String s) {
        this.topic = s;
    }
}
