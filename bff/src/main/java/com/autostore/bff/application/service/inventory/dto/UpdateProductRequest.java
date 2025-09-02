package com.autostore.bff.application.service.inventory.dto;


import lombok.Builder;
import lombok.Data;

import java.util.Map;


@Data
@Builder
public class UpdateProductRequest {

    String code;
    Double unitValue;
    String category;
    Map<String, Object> details;

}
