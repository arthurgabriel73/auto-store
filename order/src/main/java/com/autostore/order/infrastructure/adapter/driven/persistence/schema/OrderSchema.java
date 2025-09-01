package com.autostore.order.infrastructure.adapter.driven.persistence.schema;


import com.autostore.order.domain.Order;
import com.autostore.order.domain.OrderCustomer;
import com.autostore.order.domain.OrderProducts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order")
public class OrderSchema {

    @Id
    private String id;
    private String transactionId;
    private List<OrderProducts> products;
    private double totalAmount;
    private int totalItems;
    private LocalDateTime createdAt;
    private OrderCustomer customer;


    public static OrderSchema fromDomain(Order order) {
        return OrderSchema.builder()
                .id(order.getId())
                .products(order.getProducts())
                .createdAt(order.getCreatedAt())
                .transactionId(order.getTransactionId())
                .totalAmount(order.getTotalAmount())
                .totalItems(order.getTotalItems())
                .customer(order.getCustomer())
                .build();
    }

    public Order toDomain() {
        return Order.of(
                this.id,
                this.products,
                this.createdAt,
                this.transactionId,
                this.totalAmount,
                this.totalItems,
                this.customer
        );
    }

}
