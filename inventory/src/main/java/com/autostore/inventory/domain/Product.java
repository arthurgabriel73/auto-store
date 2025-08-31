package com.autostore.inventory.domain;


import lombok.Builder;
import lombok.Getter;

import java.util.Map;


@Builder
@Getter
public class Product {

    private final Long id;
    private final String code;
    private final Double unitValue;
    private final ProductCategory category;
    private Map<String, Object> details;

}
