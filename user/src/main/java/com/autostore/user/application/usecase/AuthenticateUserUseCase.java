package com.autostore.user.application.usecase;


import com.autostore.user.application.exception.UserNotFoundException;
import com.autostore.user.application.port.driven.TokenGenerator;
import com.autostore.user.application.port.driven.UserRepository;
import com.autostore.user.application.port.driver.AuthenticateUserUserDriverPort;
import com.autostore.user.application.port.driver.model.command.AuthenticateUserCommand;
import com.autostore.user.application.port.driver.model.command.AuthenticateUserCommandOutput;
import com.autostore.user.domain.Cpf;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class AuthenticateUserUseCase implements AuthenticateUserUserDriverPort {

    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;

    @Override
    public AuthenticateUserCommandOutput execute(AuthenticateUserCommand command) {
        String cpfString = command.cpf();
        if (!userRepository.existsByCpf(Cpf.of(cpfString)))
            throw new UserNotFoundException("The current user is not registered in the system.");
        String token = tokenGenerator.generateTokenWithCpf(cpfString);
        return new AuthenticateUserCommandOutput(token);
    }

}
