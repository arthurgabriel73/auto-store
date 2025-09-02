package com.autostore.bff.application.service;


import com.autostore.bff.domain.Topic;

import static com.autostore.bff.domain.Topic.*;


public class SagaExecutionController {

    public Topic getNextTopic(Topic currentTopic) {
        return switch (currentTopic) {
            case ORDER_SERVICE_ORDER_CREATED_V1 -> VALIDATE_ORDER_COMMAND;
            case VALIDATION_SERVICE_VALIDATION_UPDATED_V1 -> PROCESS_PAYMENT_COMMAND;
            case PAYMENT_SERVICE_PAYMENT_PROCESSED_V1 -> UPDATE_INVENTORY_COMMAND;
            case INVENTORY_SERVICE_INVENTORY_UPDATED_V1 -> null;
            case INVENTORY_SERVICE_INVENTORY_FAILED_V1, INVENTORY_SERVICE_INVENTORY_ROLLBACK_SUCCESS_V1 ->
                    REFUND_PAYMENT_COMMAND;
            case PAYMENT_SERVICE_PAYMENT_FAILED_V1, PAYMENT_SERVICE_PAYMENT_REFUND_SUCCESS_V1 ->
                    ROLLBACK_ORDER_VALIDATION_COMMAND;

            default -> null;
        };
    }

}
