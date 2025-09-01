package com.autostore.bff.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DomainEvent<T> {

    protected String id;
    protected String transactionId;
    protected T payload;
    protected LocalDateTime createdAt;

}
