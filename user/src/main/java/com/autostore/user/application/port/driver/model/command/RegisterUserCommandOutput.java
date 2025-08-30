package com.autostore.user.application.port.driver.model.command;


import com.autostore.user.domain.UserId;
import lombok.Getter;


public class RegisterUserCommandOutput {

    @Getter
    private final String userId;

    public RegisterUserCommandOutput(UserId userId) {
        this.userId = userId.string();
    }

}
