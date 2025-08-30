package com.autostore.user.application.usecase;


import com.autostore.user.application.exception.CpfAlreadyRegisteredException;
import com.autostore.user.application.exception.EmailAlreadyRegisteredException;
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
        requireCpfNotRegistered(user.getCpf());
        requireEmailNotRegistered(user.getEmail());
        requireUserApproved(user);
        return saveUser(user);
    }

    private void requireCpfNotRegistered(Cpf cpf) {
        if (userRepository.existsByCpf(cpf)) throw new CpfAlreadyRegisteredException(cpf.value());
    }

    private void requireEmailNotRegistered(Email email) {
        if (userRepository.existsByEmail(email)) throw new EmailAlreadyRegisteredException(email.value());
    }

    private void requireUserApproved(User user) {
        if (!userScoreService.isApproved(user.getCpf())) throw new BusinessException("User not approved");
    }

    private RegisterUserCommandOutput saveUser(User user) {
        User savedUser = userRepository.save(user);
        return RegisterUserCommandOutput.of(savedUser.getId());
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
