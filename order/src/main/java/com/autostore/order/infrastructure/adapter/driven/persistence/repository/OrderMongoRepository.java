package com.autostore.order.infrastructure.adapter.driven.persistence.repository;


import com.autostore.order.infrastructure.adapter.driven.persistence.schema.OrderSchema;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface OrderMongoRepository extends MongoRepository<OrderSchema, String> {

}
