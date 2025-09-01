package com.autostore.bff.infrastructure.adapter.driven.client;


import com.autostore.bff.application.port.driven.UserGateway;
import com.autostore.bff.application.service.model.request.AuthUserRequest;
import com.autostore.bff.application.service.model.request.RegisterUserRequest;
import com.autostore.bff.application.service.model.response.AuthUserResponse;
import com.autostore.bff.application.service.model.response.RegisterUserResponse;


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
