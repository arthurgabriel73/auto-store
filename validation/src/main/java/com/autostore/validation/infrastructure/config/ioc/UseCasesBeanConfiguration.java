package com.autostore.validation.infrastructure.config.ioc;


import com.autostore.validation.application.port.driven.EventProducer;
import com.autostore.validation.application.port.driven.ValidationProductRepository;
import com.autostore.validation.application.port.driven.ValidationRepository;
import com.autostore.validation.application.port.driver.RegisterValidationProductDriverPort;
import com.autostore.validation.application.port.driver.ValidateProductsDriverPort;
import com.autostore.validation.application.usecase.RegisterValidationProductUseCase;
import com.autostore.validation.application.usecase.ValidateProductsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UseCasesBeanConfiguration {

    @Bean
    public RegisterValidationProductDriverPort registerValidationProductDriverPort(ValidationProductRepository validationProductRepository) {

        return new RegisterValidationProductUseCase(validationProductRepository);
    }

    @Bean
    public ValidateProductsDriverPort validateProductsDriverPort(
            ValidationRepository validationRepository,
            ValidationProductRepository validationProductRepository,
            EventProducer eventProducer
    ) {

        return new ValidateProductsUseCase(
                validationRepository,
                validationProductRepository,
                eventProducer
        );
    }

}
