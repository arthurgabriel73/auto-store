package com.autostore.bff.infrastructure.config.ioc;


import com.autostore.bff.application.port.driven.EventProducer;
import com.autostore.bff.application.port.driven.InventoryGateway;
import com.autostore.bff.application.port.driven.OrderGateway;
import com.autostore.bff.application.port.driven.UserGateway;
import com.autostore.bff.application.port.driver.OrchestratorServiceDriverPort;
import com.autostore.bff.application.service.OrchestratorService;
import com.autostore.bff.application.service.SagaExecutionController;
import com.autostore.bff.application.service.inventory.InventoryService;
import com.autostore.bff.application.service.order.OrderService;
import com.autostore.bff.application.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ServiceBeanConfiguration {

    @Bean
    public UserService userService(UserGateway userGateway) {
        return new UserService(userGateway);
    }

    @Bean
    public InventoryService inventoryService(InventoryGateway inventoryGateway) {
        return new InventoryService(inventoryGateway);
    }

    @Bean
    public OrderService orderService(OrderGateway orderGateway) {
        return new OrderService(orderGateway);
    }

    @Bean
    OrchestratorServiceDriverPort orchestratorServiceDriverPort(
            EventProducer eventProducer
    ) {
        return new OrchestratorService(new SagaExecutionController(), eventProducer);
    }

}
