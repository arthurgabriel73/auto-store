package com.autostore.order.infrastructure.config.ioc;


import com.autostore.order.application.port.driven.EventProducer;
import com.autostore.order.application.port.driven.OrderRepository;
import com.autostore.order.application.port.driver.CreateOrderDriverPort;
import com.autostore.order.application.usecase.CreateOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UseCasesBeanConfiguration {

    @Bean
    public CreateOrderDriverPort createOrderDriverPort(
            OrderRepository orderRepository,
            EventProducer eventProducer
    ) {
        return new CreateOrderUseCase(orderRepository, eventProducer);
    }

}
