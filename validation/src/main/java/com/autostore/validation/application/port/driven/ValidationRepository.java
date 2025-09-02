package com.autostore.validation.application.port.driven;


import com.autostore.validation.domain.Validation;

import java.util.List;
import java.util.Optional;


public interface ValidationRepository {

    void save(Validation validation);

    Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);

    Optional<Validation> findByOrderIdAndTransactionId(String orderId, String transactionId);

    List<String> listSuccessfulOrdersIds();

}
