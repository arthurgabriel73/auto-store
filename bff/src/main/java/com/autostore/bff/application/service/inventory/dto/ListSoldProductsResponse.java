package com.autostore.bff.application.service.inventory.dto;


import java.util.List;


public record ListSoldProductsResponse(List<ProductResponse> products) {

}
