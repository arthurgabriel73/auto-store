package com.autostore.user.infrastructure.adapter.driver.rest;


import com.autostore.user.application.port.driver.AuthenticateUserUserDriverPort;
import com.autostore.user.application.port.driver.FindUserByCpfDriverPort;
import com.autostore.user.application.port.driver.RegisterUserDriverPort;
import com.autostore.user.application.port.driver.model.command.AuthenticateUserCommand;
import com.autostore.user.application.port.driver.model.command.AuthenticateUserCommandOutput;
import com.autostore.user.application.port.driver.model.command.RegisterUserCommand;
import com.autostore.user.application.port.driver.model.command.RegisterUserCommandOutput;
import com.autostore.user.application.port.driver.model.query.FindUserByCpfQuery;
import com.autostore.user.application.port.driver.model.query.FindUserByCpfQueryOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticateUserUserDriverPort authenticateUserUserDriverPort;
    private final RegisterUserDriverPort registerUserDriverPort;
    private final FindUserByCpfDriverPort findUserByCpfDriverPort;


    @PostMapping("/auth")
    public AuthenticateUserCommandOutput authenticateUser(@RequestBody AuthenticateUserCommand command) {
        return authenticateUserUserDriverPort.execute(command);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterUserCommandOutput registerUser(@RequestBody RegisterUserCommand command) {
        return registerUserDriverPort.execute(command);
    }

    @GetMapping("/cpf/{cpf}")
    public FindUserByCpfQueryOutput findUserByCpf(@PathVariable String cpf) {
        FindUserByCpfQuery query = new FindUserByCpfQuery(cpf);
        return findUserByCpfDriverPort.execute(query);
    }

}
