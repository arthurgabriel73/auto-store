package com.autostore.bff.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DomainEvent<T> {

    protected String id;
    protected String transactionId;
    protected T payload;
    protected LocalDateTime createdAt;
    protected List<History> eventHistory;

    public void addToHistory(History history) {
        if (eventHistory == null || eventHistory.isEmpty()) eventHistory = new ArrayList<>();
        eventHistory.add(history);
    }

}
