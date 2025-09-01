package com.autostore.bff.infrastructure.config.adapter.driven.persistence.schema;


import com.autostore.bff.domain.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "event")
public class EventSchema<T> {

    @Id
    private String id;
    private String orderId;
    private String transactionId;
    private T payload;
    private String source;
    private LocalDateTime createdAt;

    public static <T> EventSchema<T> fromDomain(DomainEvent<T> event) {
        return EventSchema.<T>builder()
                .id(event.getId())
                .transactionId(event.getTransactionId())
                .payload(event.getPayload())
                .createdAt(event.getCreatedAt())
                .build();
    }

    public DomainEvent<T> toDomain() {
        return DomainEvent.<T>builder()
                .id(id)
                .transactionId(transactionId)
                .payload(payload)
                .createdAt(createdAt)
                .build();
    }

}
