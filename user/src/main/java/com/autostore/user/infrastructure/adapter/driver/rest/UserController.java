package com.autostore.user.infrastructure.adapter.driver.rest;


import com.autostore.user.application.port.driver.RegisterUserDriverPort;
import com.autostore.user.application.port.driver.model.command.RegisterUserCommand;
import com.autostore.user.application.port.driver.model.command.RegisterUserCommandOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final RegisterUserDriverPort registerUserDriverPort;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterUserCommandOutput registerUser(@RequestBody RegisterUserCommand command) {
        return registerUserDriverPort.execute(command);
    }

}
