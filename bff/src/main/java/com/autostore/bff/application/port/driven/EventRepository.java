package com.autostore.bff.application.port.driven;


import com.autostore.bff.domain.DomainEvent;

import java.util.List;
import java.util.Optional;


public interface EventRepository {

    DomainEvent<?> save(DomainEvent<?> event);

    List<DomainEvent<?>> findAllByOrderByCreatedAtDesc();

    Optional<DomainEvent<?>> findTop1ByOrderIdOrderByCreatedAtDesc(String orderId);

    Optional<DomainEvent<?>> findTop1ByTransactionIdOrderByCreatedAtDesc(String transactionId);

}
