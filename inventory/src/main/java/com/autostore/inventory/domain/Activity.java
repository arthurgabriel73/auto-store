package com.autostore.inventory.domain;


import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record Activity(
        Long id,
        Inventory inventory,
        String orderId,
        String transactionId,
        Integer orderQuantity,
        Integer oldQuantity,
        Integer newQuantity,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}
