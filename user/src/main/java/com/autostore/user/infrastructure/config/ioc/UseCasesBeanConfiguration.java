package com.autostore.user.infrastructure.config.ioc;


import com.autostore.user.application.port.driven.TokenGenerator;
import com.autostore.user.application.port.driven.UserRepository;
import com.autostore.user.application.port.driven.UserScoreService;
import com.autostore.user.application.port.driver.AuthenticateUserUserDriverPort;
import com.autostore.user.application.port.driver.FindUserByCpfDriverPort;
import com.autostore.user.application.port.driver.RegisterUserDriverPort;
import com.autostore.user.application.usecase.AuthenticateUserUseCase;
import com.autostore.user.application.usecase.FindUserByCpfUseCase;
import com.autostore.user.application.usecase.RegisterUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UseCasesBeanConfiguration {

    @Bean
    AuthenticateUserUserDriverPort authenticateUserUserDriverPort(UserRepository userRepository, TokenGenerator tokenGenerator) {
        return new AuthenticateUserUseCase(userRepository, tokenGenerator);
    }

    @Bean
    RegisterUserDriverPort registerUserDriverPort(UserRepository userRepository, UserScoreService userScoreService) {
        return new RegisterUserUseCase(userRepository, userScoreService);
    }

    @Bean
    FindUserByCpfDriverPort findUserByCpfDriverPort(UserRepository userRepository) {
        return new FindUserByCpfUseCase(userRepository);
    }

}
