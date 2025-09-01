package com.autostore.payment.application.port.driven;


import com.autostore.payment.domain.Payment;

import java.util.Optional;


public interface PaymentRepository {

    void save(Payment payment);

    Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);

    Optional<Payment> findByOrderIdAndTransactionId(String orderId, String transactionId);

}
