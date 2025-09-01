package com.autostore.bff.application.port.driven;


import com.autostore.bff.application.service.user.dto.AuthUserRequest;
import com.autostore.bff.application.service.user.dto.AuthUserResponse;
import com.autostore.bff.application.service.user.dto.RegisterUserRequest;
import com.autostore.bff.application.service.user.dto.RegisterUserResponse;


public interface UserGateway {

    AuthUserResponse authUser(AuthUserRequest request);

    RegisterUserResponse registerUser(RegisterUserRequest request);

}
