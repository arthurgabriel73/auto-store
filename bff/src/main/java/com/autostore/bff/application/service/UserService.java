package com.autostore.bff.application.service;


import com.autostore.bff.application.port.driven.UserGateway;
import com.autostore.bff.application.service.model.request.AuthUserRequest;
import com.autostore.bff.application.service.model.request.RegisterUserRequest;
import com.autostore.bff.application.service.model.response.AuthUserResponse;
import com.autostore.bff.application.service.model.response.RegisterUserResponse;
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
