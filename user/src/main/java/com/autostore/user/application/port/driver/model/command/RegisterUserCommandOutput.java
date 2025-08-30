package com.autostore.user.application.port.driver.model.command;


import com.autostore.user.domain.UserId;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class RegisterUserCommandOutput {

    private String userId;

    private RegisterUserCommandOutput(String userId) {
        this.userId = userId;
    }

    public static RegisterUserCommandOutput of(UserId userId) {
        return new RegisterUserCommandOutput(userId.string());
    }

}
