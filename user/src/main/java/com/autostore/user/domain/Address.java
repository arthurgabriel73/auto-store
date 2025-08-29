package com.autostore.user.domain;


public record Address(
        Long id,
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
