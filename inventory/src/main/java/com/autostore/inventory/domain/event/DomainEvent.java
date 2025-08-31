package com.autostore.inventory.domain.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@SuperBuilder
@Getter
@AllArgsConstructor
public class DomainEvent<T> {

    protected final String id;
    protected final T payload;
    protected final LocalDateTime createdAt;

}
