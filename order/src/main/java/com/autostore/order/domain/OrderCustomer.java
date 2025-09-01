package com.autostore.order.domain;


import com.autostore.order.domain.exception.BusinessException;


public record OrderCustomer(String customerId) {

    public OrderCustomer {
        validateCustomerId(customerId);
    }

    private void validateCustomerId(String customerId) {
        if (customerId == null || customerId.isBlank())
            throw new BusinessException("Cannot create order without customer.");
    }

}
