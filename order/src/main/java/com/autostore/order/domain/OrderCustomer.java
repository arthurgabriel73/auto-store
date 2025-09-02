package com.autostore.order.domain;


import com.autostore.order.domain.exception.BusinessException;
import lombok.Getter;


@Getter
public class OrderCustomer {

    private final String customer;

    private OrderCustomer(String customer) {
        validateCustomer(customer);
        this.customer = customer;
    }

    public static OrderCustomer of(String customer) {
        return new OrderCustomer(customer);
    }

    private void validateCustomer(String customer) {
        if (customer == null || customer.isBlank())
            throw new BusinessException("Cannot create order without customer.");
    }

}
