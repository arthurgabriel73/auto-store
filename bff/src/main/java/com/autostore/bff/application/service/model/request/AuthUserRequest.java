package com.autostore.bff.application.service.model.request;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AuthUserRequest {

    private String cpf;

}
