package com.autostore.validation.infrastructure.adapter.driver.rest;


import com.autostore.validation.application.port.driver.RegisterValidationProductDriverPort;
import com.autostore.validation.application.port.driver.model.command.RegisterValidationProductCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/validation")
@RequiredArgsConstructor
public class ValidationController {

    private final RegisterValidationProductDriverPort registerValidationProductDriverPort;

    @PostMapping("/register-product")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerValidationProduct(@RequestBody RegisterValidationProductCommand command) {
        registerValidationProductDriverPort.execute(command);
    }

}
