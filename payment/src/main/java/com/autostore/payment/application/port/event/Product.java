package com.autostore.payment.application.port.event;


import lombok.Builder;

import java.util.Map;


@Builder
public record Product(
        Long id,
        String code,
        Double unitValue,
        String category,
        Map<String, Object> details
) {

}
