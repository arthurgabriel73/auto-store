package com.autostore.bff.application.service.order;


import com.autostore.bff.application.port.driven.ValidationGateway;
import com.autostore.bff.application.service.order.dto.RegisterProductValidationRequest;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ValidationService {

    private final ValidationGateway validationGateway;

    public void registerProductValidation(RegisterProductValidationRequest request) {
        validationGateway.registerProductValidation(request);
    }

}
