package com.autostore.user.application.port.driver.model.command;


public record RegisterUserCommand(
        String firstName,
        String lastName,
        String cpf,
        String email,
        String street,
        String number,
        String neighborhood,
        String city,
        String state,
        String zipCode,
        String complement,
        String country

) {

}
