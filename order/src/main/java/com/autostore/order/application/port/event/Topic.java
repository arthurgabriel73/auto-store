package com.autostore.order.application.port.event;


import lombok.Getter;


@Getter
public enum Topic {
    ORDER_SERVICE_ORDER_CREATED_V1("order-service-order-created-v1"),
    ORDER_SERVICE_ORDER_FAILED_V1("order-service-order-failed-v1");


    private final String topic;

    Topic(String s) {
        this.topic = s;
    }
}
