package com.autostore.order.domain;


import com.autostore.order.domain.exception.BusinessException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
public class Order {

    private static final Double REDUCE_SUM_VALUE = 0.0;
    private static final Double MIN_VALUE_AMOUNT = 0.1;

    private final String id;
    private final List<OrderProducts> products;
    private final LocalDateTime createdAt;
    private final String transactionId;
    private final double totalAmount;
    private final int totalItems;
    private final OrderCustomer customer;

    private Order(String id, List<OrderProducts> products, LocalDateTime createdAt, String transactionId,
                  double totalAmount, int totalItems, OrderCustomer customer) {
        this.id = id;
        this.products = products;
        this.createdAt = createdAt;
        this.transactionId = transactionId;
        this.totalAmount = totalAmount;
        this.totalItems = totalItems;
        this.customer = customer;
    }

    private Order(List<OrderProducts> products, LocalDateTime createdAt, String transactionId, double totalAmount,
                  int totalItems, OrderCustomer customer) {
        this.id = null;
        this.products = products;
        this.createdAt = createdAt;
        this.transactionId = transactionId;
        this.totalAmount = totalAmount;
        this.totalItems = totalItems;
        this.customer = customer;
    }

    public static Order create(List<OrderProducts> products, String transactionId, OrderCustomer customer) {
        var createdAt = LocalDateTime.now();
        var totalAmount = calculateTotalAmount(products);
        var totalItems = calculateTotalItems(products);
        validateItems(totalItems);
        validateAmount(totalAmount);
        return new Order(products, createdAt, transactionId, totalAmount, totalItems, customer);
    }

    public static Order of(
            String id,
            List<OrderProducts> products,
            LocalDateTime createdAt,
            String transactionId,
            double totalAmount,
            int totalItems,
            OrderCustomer customer
    ) {
        return new Order(id, products, createdAt, transactionId, totalAmount, totalItems, customer);
    }

    private static double calculateTotalAmount(List<OrderProducts> products) {
        return products.stream()
                .map(product -> product.quantity() * product.product().unitValue())
                .reduce(REDUCE_SUM_VALUE, Double::sum);
    }

    private static int calculateTotalItems(List<OrderProducts> products) {
        return products.stream()
                .map(OrderProducts::quantity)
                .reduce(REDUCE_SUM_VALUE.intValue(), Integer::sum);
    }

    private static void validateAmount(double totalAmount) {
        if (totalAmount < MIN_VALUE_AMOUNT) throw new BusinessException("Total amount must be greater than zero");
    }

    private static void validateItems(int totalItems) {
        if (totalItems < 1) throw new BusinessException("Total items must be at least one");
    }

}
