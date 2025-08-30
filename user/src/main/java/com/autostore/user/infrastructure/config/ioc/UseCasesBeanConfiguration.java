package com.autostore.user.infrastructure.config.ioc;


import com.autostore.user.application.port.driven.UserRepository;
import com.autostore.user.application.port.driven.UserScoreService;
import com.autostore.user.application.port.driver.RegisterUserDriverPort;
import com.autostore.user.application.usecase.RegisterUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UseCasesBeanConfiguration {

    @Bean
    RegisterUserDriverPort registerUserDriverPort(UserRepository userRepository, UserScoreService userScoreService) {
        return new RegisterUserUseCase(userRepository, userScoreService);
    }

}
