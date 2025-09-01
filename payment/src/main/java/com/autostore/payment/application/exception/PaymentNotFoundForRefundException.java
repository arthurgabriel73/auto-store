package com.autostore.payment.application.exception;


public class PaymentNotFoundForRefundException extends ApplicationException {

    public PaymentNotFoundForRefundException(String message) {
        super(message);
    }

}
