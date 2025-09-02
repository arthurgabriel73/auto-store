package com.autostore.bff.infrastructure.adapter.driven.client;


import com.autostore.bff.application.port.driven.UserGateway;
import com.autostore.bff.application.service.user.dto.AuthUserRequest;
import com.autostore.bff.application.service.user.dto.AuthUserResponse;
import com.autostore.bff.application.service.user.dto.RegisterUserRequest;
import com.autostore.bff.application.service.user.dto.RegisterUserResponse;
import jakarta.inject.Named;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@Named
@FeignClient(name = "user-client", url = "${feign.client.config.user-service.url}")
public interface UserClient extends UserGateway {

    @Override
    @PostMapping("/auth")
    AuthUserResponse authUser(AuthUserRequest request);

    @Override
    @PostMapping
    RegisterUserResponse registerUser(RegisterUserRequest request);

}
