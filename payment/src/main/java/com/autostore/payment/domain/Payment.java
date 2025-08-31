package com.autostore.payment.domain;


import com.autostore.payment.domain.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Builder
@Getter
@AllArgsConstructor
public class Payment {

    private final Long id;
    private final String orderId;
    private final String transactionId;
    private final Integer totalItems;
    private final Double totalAmount;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private PaymentStatus status;

    public void confirmPayment() {
        if (this.status != PaymentStatus.PENDING)
            throw new BusinessException("Payment can only be confirmed from PENDING status");
        this.status = PaymentStatus.SUCCESS;
    }

    public void refundPayment() {
        if (this.status != PaymentStatus.SUCCESS)
            throw new BusinessException("Payment can only be refunded from SUCCESS status, try CANCEL instead");
        this.status = PaymentStatus.REFUND;
    }

    public void cancelPayment() {
        if (this.status != PaymentStatus.PENDING)
            throw new BusinessException("Payment can only be cancelled from PENDING status, try REFUND instead");
        this.status = PaymentStatus.CANCELLED;
    }

}
