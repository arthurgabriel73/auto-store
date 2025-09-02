package com.autostore.bff.application.service.inventory.dto;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AddProductStockRequest {

    String productCode;
    Integer quantity;

}
