package com.autostore.bff.infrastructure.config.adapter.driven.persistence;


import com.autostore.bff.application.port.driven.EventRepository;
import com.autostore.bff.domain.DomainEvent;
import com.autostore.bff.infrastructure.config.adapter.driven.persistence.repository.EventMongoRepository;
import com.autostore.bff.infrastructure.config.adapter.driven.persistence.schema.EventSchema;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@Named
@RequiredArgsConstructor
public class EventRepositoryAdapter<T> implements EventRepository<T> {

    private final EventMongoRepository<T> repository;

    @Override
    public DomainEvent<T> save(DomainEvent<T> event) {
        EventSchema<T> eventSchema = EventSchema.fromDomain(event);
        return repository.save(eventSchema).toDomain();
    }

    @Override
    public Optional<DomainEvent<T>> findTop1ByTransactionIdOrderByCreatedAtDesc(String transactionId) {
        var eventSchema = repository.findTop1ByTransactionIdOrderByCreatedAtDesc(transactionId);
        if (eventSchema.isEmpty()) return Optional.empty();
        return eventSchema.map(EventSchema::toDomain);
    }

}
