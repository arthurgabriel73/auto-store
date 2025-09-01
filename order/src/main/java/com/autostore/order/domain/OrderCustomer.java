package com.autostore.order.domain;


import com.autostore.order.domain.exception.BusinessException;
import lombok.Getter;


@Getter
public class OrderCustomer {

    private final String customerId;

    private OrderCustomer(String customerId) {
        validateCustomerId(customerId);
        this.customerId = customerId;
    }

    public static OrderCustomer of(String customerId) {
        return new OrderCustomer(customerId);
    }

    private void validateCustomerId(String customerId) {
        if (customerId == null || customerId.isBlank())
            throw new BusinessException("Cannot create order without customer.");
    }

}
