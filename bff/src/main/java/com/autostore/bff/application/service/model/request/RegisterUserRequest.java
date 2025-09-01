package com.autostore.bff.application.service.model.request;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class RegisterUserRequest {

    private String firstName;
    private String lastName;
    private String cpf;
    private String email;
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;
    private String complement;
    private String country;

}
