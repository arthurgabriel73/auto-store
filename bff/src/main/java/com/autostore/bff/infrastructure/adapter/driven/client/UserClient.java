package com.autostore.bff.infrastructure.adapter.driven.client;


import com.autostore.bff.application.port.driven.UserGateway;
import com.autostore.bff.application.service.user.dto.AuthUserRequest;
import com.autostore.bff.application.service.user.dto.AuthUserResponse;
import com.autostore.bff.application.service.user.dto.RegisterUserRequest;
import com.autostore.bff.application.service.user.dto.RegisterUserResponse;


public class UserClient implements UserGateway {

    @Override
    public AuthUserResponse authUser(AuthUserRequest request) {
        // TODO
        return null;
    }

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        // TODO
        return null;
    }

}
