package com.autostore.bff.infrastructure.adapter.driven.client;


import com.autostore.bff.application.port.driven.ValidationGateway;
import com.autostore.bff.application.service.order.dto.RegisterProductValidationRequest;
import jakarta.inject.Named;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@Named
@FeignClient(name = "validation-client", url = "${feign.client.config.validation-service.url}")
public interface ValidationClient extends ValidationGateway {

    @Override
    @PostMapping("/register-product")
    void registerProductValidation(RegisterProductValidationRequest request);

}
