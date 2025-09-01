package com.autostore.payment.application.port.driven;


import com.autostore.payment.domain.Payment;

import java.util.Optional;


public interface PaymentRepository {

    Payment save(Payment payment);

    Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);

    Optional<Payment> findByOrderIdAndTransactionId(String orderId, String transactionId);

}
