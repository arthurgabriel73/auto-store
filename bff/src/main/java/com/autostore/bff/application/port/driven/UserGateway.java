package com.autostore.bff.application.port.driven;


import com.autostore.bff.application.service.model.request.AuthUserRequest;
import com.autostore.bff.application.service.model.request.RegisterUserRequest;
import com.autostore.bff.application.service.model.response.AuthUserResponse;
import com.autostore.bff.application.service.model.response.RegisterUserResponse;


public interface UserGateway {

    AuthUserResponse authUser(AuthUserRequest request);

    RegisterUserResponse registerUser(RegisterUserRequest request);

}
