package com.autostore.validation.domain.event;


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
