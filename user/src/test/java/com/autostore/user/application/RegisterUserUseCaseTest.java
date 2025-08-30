package com.autostore.user.application;


import com.autostore.user.application.exception.CpfAlreadyRegisteredException;
import com.autostore.user.application.exception.EmailAlreadyRegisteredException;
import com.autostore.user.application.port.driver.model.command.RegisterUserCommand;
import com.autostore.user.application.usecase.RegisterUserUseCase;
import com.autostore.user.domain.Cpf;
import com.autostore.user.domain.exception.BusinessException;
import com.autostore.user.infrastructure.adapter.driven.api.FakeUserScoreService;
import com.autostore.user.infrastructure.adapter.driven.persistence.InMemoryUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class RegisterUserUseCaseTest {

    private static final RegisterUserCommand command = new RegisterUserCommand(
            "John",
            "Doe",
            "047.797.980-78",
            "john@mail.com",
            "Main St",
            "123",
            "Downtown",
            "Metropolis",
            "State",
            "12345",
            "Apt 1",
            "Country"
    );
    private InMemoryUserRepository userRepository;
    private FakeUserScoreService userScoreService;
    private RegisterUserUseCase sut;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
        userScoreService = new FakeUserScoreService();
        sut = new RegisterUserUseCase(userRepository, userScoreService);
    }

    @Test
    void testShouldRegisterUserSuccessfully() {
        // Arrange
        userScoreService.setApproved(true);

        // Act
        var output = sut.execute(command);

        // Assert
        assertNotNull(output);
        assertNotNull(output.getUserId());
        assertNotNull(userRepository.findByCpf(Cpf.of(command.cpf())));
    }

    @Test
    void testShouldThrowBusinessExceptionWhenUserCpfIsNotApproved() {
        // Arrange
        userScoreService.setApproved(false);

        // Act & Assert
        assertThrows(BusinessException.class, () -> sut.execute(command));
    }

    @Test
    void testShouldThrowExceptionWhenUserAlreadyExistsByEmail() {
        // Arrange
        userScoreService.setApproved(true);
        sut.execute(command);
        RegisterUserCommand newCommand = new RegisterUserCommand(
                "John",
                "Doe",
                "296.359.920-09",
                "john@mail.com",
                "Main St",
                "123",
                "Downtown",
                "Metropolis",
                "State",
                "12345",
                "Apt 1",
                "Country"
        );

        // Act & Assert
        assertThrows(EmailAlreadyRegisteredException.class, () -> sut.execute(newCommand));
    }

    @Test
    void testShouldThrowExceptionWhenUserAlreadyExistsByCpf() {
        // Arrange
        userScoreService.setApproved(true);
        sut.execute(command);
        RegisterUserCommand newCommand = new RegisterUserCommand(
                "John",
                "Doe",
                "047.797.980-78",
                "john2@mail.com",
                "Main St",
                "123",
                "Downtown",
                "Metropolis",
                "State",
                "12345",
                "Apt 1",
                "Country"
        );

        // Act & Assert
        assertThrows(CpfAlreadyRegisteredException.class, () -> sut.execute(newCommand));
    }

}
