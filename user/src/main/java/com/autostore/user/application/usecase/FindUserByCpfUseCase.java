package com.autostore.user.application.usecase;


import com.autostore.user.application.exception.UserNotFoundException;
import com.autostore.user.application.port.driven.UserRepository;
import com.autostore.user.application.port.driver.FindUserByCpfDriverPort;
import com.autostore.user.application.port.driver.model.query.FindUserByCpfQuery;
import com.autostore.user.application.port.driver.model.query.FindUserByCpfQueryOutput;
import com.autostore.user.domain.Cpf;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class FindUserByCpfUseCase implements FindUserByCpfDriverPort {

    private final UserRepository userRepository;

    @Override
    public FindUserByCpfQueryOutput execute(FindUserByCpfQuery query) {
        Cpf userCpf = Cpf.of(query.cpf());
        var userFound = userRepository.findByCpf(userCpf);
        if (userFound.isEmpty()) throw new UserNotFoundException("User with CPF " + query.cpf() + " not found");
        return new FindUserByCpfQueryOutput(userFound.get());
    }

}
