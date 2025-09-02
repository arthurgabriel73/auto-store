package com.autostore.bff.application.port.driven;


import com.autostore.bff.application.service.order.dto.RegisterProductValidationRequest;

import java.util.List;


public interface ValidationGateway {

    void registerProductValidation(RegisterProductValidationRequest request);

    List<String> listSuccessfulOrdersIds();

}
