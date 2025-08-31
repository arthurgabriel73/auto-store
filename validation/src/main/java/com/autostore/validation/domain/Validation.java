package com.autostore.validation.domain;


import com.autostore.validation.domain.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Builder
@Getter
@AllArgsConstructor
public class Validation {

    private final Long id;
    private final String orderId;
    private final String transactionId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private boolean success;


    public void markFailed() {
        if (!success) throw new BusinessException("Validation is already marked as failed.");
        this.success = false;
    }

}
