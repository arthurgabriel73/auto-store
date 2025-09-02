package com.autostore.bff.application.port.driven;


import com.autostore.bff.domain.DomainEvent;

import java.util.Optional;


public interface EventRepository<T> {

    void save(DomainEvent<T> event);

    Optional<DomainEvent<T>> findTop1ByTransactionIdOrderByCreatedAtDesc(String transactionId);

}
