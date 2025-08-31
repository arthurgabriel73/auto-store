package com.autostore.inventory.domain.event;


import lombok.Getter;


@Getter
public enum Topic {
    INVENTORY_SERVICE_INVENTORY_UPDATED_V1("inventory-service-inventory-updated-v1"),
    INVENTORY_SERVICE_INVENTORY_FAILED_V1("inventory-service-inventory-failed-v1"),
    INVENTORY_SERVICE_INVENTORY_ROLLBACK_SUCCESS_V1("inventory-service-inventory-rollback-success-v1"),
    INVENTORY_SERVICE_INVENTORY_ROLLBACK_FAILED_V1("inventory-service-inventory-rollback-failed-v1");


    private final String topic;

    Topic(String s) {
        this.topic = s;
    }
}
