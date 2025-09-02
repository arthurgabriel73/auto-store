package com.autostore.bff.application.port.driven;


import com.autostore.bff.application.service.order.dto.RegisterProductValidationRequest;


public interface ValidationGateway {

    void registerProductValidation(RegisterProductValidationRequest request);

}
