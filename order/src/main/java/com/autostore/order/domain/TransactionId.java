package com.autostore.order.domain;


import lombok.Getter;

import java.time.Instant;
import java.util.UUID;


@Getter
public class TransactionId {

    private static final String TRANSACTION_ID_PATTERN = "%s_%s";

    private final String value;

    private TransactionId() {
        this.value = String.format(TRANSACTION_ID_PATTERN, Instant.now().toEpochMilli(), UUID.randomUUID());
    }

    public static TransactionId generate() {
        return new TransactionId();
    }

    public String value() {
        return value;
    }

}
