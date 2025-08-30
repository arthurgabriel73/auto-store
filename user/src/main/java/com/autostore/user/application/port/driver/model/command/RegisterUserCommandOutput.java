package com.autostore.user.application.port.driver.model.command;


import com.autostore.user.domain.UserId;


public record RegisterUserCommandOutput(String userId) {

    public static RegisterUserCommandOutput of(UserId userId) {
        return new RegisterUserCommandOutput(userId.string());
    }

}
