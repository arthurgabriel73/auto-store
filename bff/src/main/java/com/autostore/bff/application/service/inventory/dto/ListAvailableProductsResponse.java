package com.autostore.bff.application.service.inventory.dto;


import java.util.List;


public record ListAvailableProductsResponse(List<ProductResponseWithDetails> products) {

}
