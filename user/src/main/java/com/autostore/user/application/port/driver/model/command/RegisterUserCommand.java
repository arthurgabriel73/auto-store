package com.autostore.user.application.port.driver.model.command;


import com.autostore.user.domain.Address;


public record RegisterUserCommand(
        String firstName,
        String lastName,
        String cpf,
        String email,
        Address address

) {

}
