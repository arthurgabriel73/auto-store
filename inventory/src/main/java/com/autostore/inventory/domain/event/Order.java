package com.autostore.inventory.domain.event;


import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;


@Builder
public record Order(
        String id,
        List<OrderProducts> products,
        LocalDateTime createdAt,
        String transactionId,
        double totalAmount,
        int totalItems
) {

}
