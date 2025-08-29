package com.autostore.user.domain;


import java.util.UUID;


public record UserId(UUID id) {

    public String string() {
        return id.toString();
    }

}
