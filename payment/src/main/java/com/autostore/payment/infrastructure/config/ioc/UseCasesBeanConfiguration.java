package com.autostore.payment.infrastructure.config.ioc;


import com.autostore.payment.application.port.driven.EventProducer;
import com.autostore.payment.application.port.driven.PaymentRepository;
import com.autostore.payment.application.port.driver.ProcessPaymentDriverPort;
import com.autostore.payment.application.usecase.ProcessPaymentUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UseCasesBeanConfiguration {

    @Bean
    ProcessPaymentDriverPort processPaymentDriverPort(
            PaymentRepository paymentRepository,
            EventProducer eventProducer
    ) {
        return new ProcessPaymentUseCase(paymentRepository, eventProducer);
    }

}
