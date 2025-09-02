package com.autostore.bff.application.service.user;


import com.autostore.bff.application.port.driven.UserGateway;
import com.autostore.bff.application.service.user.dto.AuthUserRequest;
import com.autostore.bff.application.service.user.dto.AuthUserResponse;
import com.autostore.bff.application.service.user.dto.RegisterUserRequest;
import com.autostore.bff.application.service.user.dto.RegisterUserResponse;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserService {

    private final UserGateway userGateway;

    public AuthUserResponse authUser(AuthUserRequest request) {
        return userGateway.authUser(request);
    }

    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        return userGateway.registerUser(request);
    }

}
