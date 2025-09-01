package com.autostore.bff.application.service.inventory.dto;


import lombok.Builder;
import lombok.Data;

import java.util.Map;


@Data
@Builder
public class RegisterProductRequest {

    private String code;
    private Double unitValue;
    private String category;
    private Map<String, Object> details;

}
