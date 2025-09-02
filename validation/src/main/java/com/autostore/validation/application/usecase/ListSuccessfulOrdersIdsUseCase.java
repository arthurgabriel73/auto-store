package com.autostore.validation.application.usecase;


import com.autostore.validation.application.port.driven.ValidationRepository;
import com.autostore.validation.application.port.driver.ListSuccessfulOrdersIdsDriverPort;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class ListSuccessfulOrdersIdsUseCase implements ListSuccessfulOrdersIdsDriverPort {

    private final ValidationRepository validationRepository;

    @Override
    public List<String> execute() {
        return validationRepository.listSuccessfulOrdersIds();
    }

}
