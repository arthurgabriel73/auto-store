package com.autostore.user.domain;


public record FullName(String firstName, String lastName) {

    public String completeName() {
        return firstName + " " + lastName;
    }

}
