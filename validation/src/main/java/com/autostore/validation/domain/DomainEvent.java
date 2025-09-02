package com.autostore.validation.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DomainEvent<T> {

    protected String id;
    protected String transactionId;
    protected T payload;
    protected LocalDateTime createdAt;

}
