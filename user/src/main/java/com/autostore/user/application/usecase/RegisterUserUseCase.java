package com.autostore.user.application.usecase;


import com.autostore.user.application.port.driven.UserRepository;
import com.autostore.user.application.port.driven.UserScoreService;
import com.autostore.user.application.port.driver.RegisterUserDriverPort;
import com.autostore.user.application.port.driver.model.command.RegisterUserCommand;
import com.autostore.user.application.port.driver.model.command.RegisterUserCommandOutput;
import com.autostore.user.domain.*;
import com.autostore.user.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@RequiredArgsConstructor
public class RegisterUserUseCase implements RegisterUserDriverPort {

    private final UserRepository userRepository;
    private final UserScoreService userScoreService;

    @Override
    public RegisterUserCommandOutput execute(RegisterUserCommand command) {
        User user = createUserEntityFromCommand(command);
        if (!userScoreService.isApproved(user.getCpf())) throw new BusinessException("User not approved");
        return new RegisterUserCommandOutput(userRepository.save(user).getId());
    }

    private User createUserEntityFromCommand(RegisterUserCommand command) {
        UserId userId = UserId.generateNew();
        FullName fullName = FullName.of(command.firstName(), command.lastName());
        Cpf cpf = Cpf.of(command.cpf());
        Email email = Email.of(command.email());
        LocalDateTime now = LocalDateTime.now();
        Address address = createAddressFromCommand(command);
        return User
                .builder()
                .id(userId)
                .fullName(fullName)
                .cpf(cpf)
                .email(email)
                .createdAt(now)
                .updatedAt(now)
                .address(address)
                .build();
    }

    private Address createAddressFromCommand(RegisterUserCommand command) {
        return Address.create(
                command.street(),
                command.number(),
                command.neighborhood(),
                command.city(),
                command.state(),
                command.zipCode(),
                command.complement(),
                command.country()
        );
    }

}
