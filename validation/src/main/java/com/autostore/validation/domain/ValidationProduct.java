package com.autostore.validation.domain;


import lombok.Builder;


@Builder
public record ValidationProduct(Long id, String code) {

}
