package com.autostore.payment.application.usecase;


import com.autostore.payment.application.exception.PaymentNotFoundForRefundException;
import com.autostore.payment.application.exception.ValidationException;
import com.autostore.payment.application.port.driven.EventProducer;
import com.autostore.payment.application.port.driven.PaymentRepository;
import com.autostore.payment.application.port.driver.ProcessPaymentDriverPort;
import com.autostore.payment.application.port.driver.model.command.ProcessPaymentCommand;
import com.autostore.payment.application.port.event.OrderEvent;
import com.autostore.payment.application.port.event.Topic;
import com.autostore.payment.domain.Payment;
import com.autostore.payment.domain.PaymentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;


@Slf4j
@RequiredArgsConstructor
public class ProcessPaymentUseCase implements ProcessPaymentDriverPort {

    private final PaymentRepository paymentRepository;
    private final EventProducer eventProducer;

    @Override
    public void execute(ProcessPaymentCommand command) {
        var event = command.event();
        try {
            requirePaymentNotDuplicated(event);
            var payment = registerPendingPayment(event);
            confirmPayment(payment);
            eventProducer.sendEvent(event, Topic.PAYMENT_SERVICE_PAYMENT_PROCESSED_V1.getTopic());
        } catch (Exception ex) {
            log.error("Error processing payment: ", ex);
            eventProducer.sendEvent(event, Topic.PAYMENT_SERVICE_PAYMENT_FAILED_V1.getTopic());
        }
    }

    private void requirePaymentNotDuplicated(OrderEvent event) {
        var orderId = event.getPayload().id();
        var transactionId = event.getPayload().transactionId();
        var payment = paymentRepository.findByOrderIdAndTransactionId(orderId, transactionId);
        if (payment.isPresent() && payment.get().isPending())
            throw new ValidationException("There's another PENDING payment for this order.");
    }

    private Payment registerPendingPayment(OrderEvent event) {
        var now = LocalDateTime.now();
        var orderId = event.getPayload().id();
        var transactionId = event.getPayload().transactionId();
        var totalAmount = event.getPayload().totalAmount();
        var totalItems = event.getPayload().totalItems();
        var payment = Payment
                .builder()
                .id(null)
                .orderId(orderId)
                .transactionId(transactionId)
                .totalItems(totalItems)
                .totalAmount(totalAmount)
                .status(PaymentStatus.PENDING)
                .createdAt(now)
                .updatedAt(now)
                .build();
        return paymentRepository.save(payment);
    }


    private void confirmPayment(Payment payment) {
        payment.confirmPayment();
        paymentRepository.save(payment);
    }

    @Override
    public void rollback(OrderEvent event) {
        try {
            refundPayment(event);
            eventProducer.sendEvent(event, Topic.PAYMENT_SERVICE_PAYMENT_REFUND_SUCCESS_V1.getTopic());
        } catch (Exception ex) {
            log.error("Error trying to refund payment: ".concat(ex.getMessage()));
            eventProducer.sendEvent(event, Topic.PAYMENT_SERVICE_PAYMENT_REFUND_FAILED_V1.getTopic());
        }
    }

    private void refundPayment(OrderEvent event) {
        var orderId = event.getPayload().id();
        var transactionId = event.getPayload().transactionId();
        paymentRepository.findByOrderIdAndTransactionId(orderId, transactionId).ifPresentOrElse(
                payment -> {
                    payment.refundPayment();
                    paymentRepository.save(payment);
                }, () -> {
                    throw new PaymentNotFoundForRefundException("Payment not found for refund.");
                });
    }

}
