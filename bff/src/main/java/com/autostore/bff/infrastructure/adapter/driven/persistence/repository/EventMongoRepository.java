package com.autostore.bff.infrastructure.adapter.driven.persistence.repository;


import com.autostore.bff.infrastructure.adapter.driven.persistence.schema.EventSchema;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface EventMongoRepository<T> extends MongoRepository<EventSchema<T>, String> {

    Optional<EventSchema<T>> findTop1ByTransactionIdOrderByCreatedAtDesc(String transactionId);

}
