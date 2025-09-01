package com.autostore.validation.application.port.event;


import lombok.Getter;


@Getter
public enum Topic {
    VALIDATION_SERVICE_VALIDATION_UPDATED_V1("validation-service-validation-updated-v1"),
    VALIDATION_SERVICE_VALIDATION_FAILED_V1("validation-service-validation-failed-v1"),
    VALIDATION_SERVICE_VALIDATION_ROLLBACK_SUCCESS_V1("validation-service-validation-rollback-success-v1"),
    VALIDATION_SERVICE_VALIDATION_ROLLBACK_FAILED_V1("validation-service-validation-rollback-failed-v1");


    private final String topic;

    Topic(String s) {
        this.topic = s;
    }
}
