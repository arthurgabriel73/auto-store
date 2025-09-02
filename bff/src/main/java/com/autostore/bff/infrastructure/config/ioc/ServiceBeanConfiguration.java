package com.autostore.bff.infrastructure.config.ioc;


import com.autostore.bff.application.port.driven.*;
import com.autostore.bff.application.port.driver.OrderOrchestratorServiceDriverPort;
import com.autostore.bff.application.service.OrderOrchestratorService;
import com.autostore.bff.application.service.SagaExecutionController;
import com.autostore.bff.application.service.inventory.InventoryService;
import com.autostore.bff.application.service.order.OrderService;
import com.autostore.bff.application.service.user.UserService;
import com.autostore.bff.domain.order.Order;
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
    OrderOrchestratorServiceDriverPort orchestratorServiceDriverPort(
            EventRepository<Order> eventRepository,
            EventProducer<Order> eventProducer
    ) {
        return new OrderOrchestratorService(new SagaExecutionController(), eventRepository, eventProducer);
    }

}
