package com.autostore.user.domain;


import java.util.UUID;


public class UserId {

    private final UUID id;

    private UserId(UUID id) {
        this.id = id;
    }

    public static UserId generateNew() {
        return new UserId(UUID.randomUUID());
    }

    public static UserId of(String id) {
        return new UserId(UUID.fromString(id));
    }

    public String string() {
        return id.toString();
    }

}
