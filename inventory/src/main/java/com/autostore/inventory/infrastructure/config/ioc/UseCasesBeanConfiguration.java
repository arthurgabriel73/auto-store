package com.autostore.inventory.infrastructure.config.ioc;


import com.autostore.inventory.application.port.driven.ActivityRepository;
import com.autostore.inventory.application.port.driven.EventProducer;
import com.autostore.inventory.application.port.driven.InventoryRepository;
import com.autostore.inventory.application.port.driven.ProductRepository;
import com.autostore.inventory.application.port.driver.*;
import com.autostore.inventory.application.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UseCasesBeanConfiguration {

    @Bean
    AddProductStockDriverPort addProductStockDriverPort(
            ProductRepository productRepository,
            InventoryRepository inventoryRepository
    ) {
        return new AddProductStockUseCase(productRepository, inventoryRepository);
    }

    @Bean
    public RegisterProductDriverPort registerProductDriverPort(
            ProductRepository productRepository,
            InventoryRepository inventoryRepository
    ) {
        return new RegisterProductUseCase(productRepository, inventoryRepository);
    }

    @Bean
    public UpdateProductDriverPort updateProductDriverPort(
            ProductRepository productRepository
    ) {
        return new UpdateProductUseCase(productRepository);
    }

    @Bean
    public UpdateInventoryDriverPort updateInventoryDriverPort(
            ActivityRepository activityRepository,
            InventoryRepository inventoryRepository,
            EventProducer eventProducer
    ) {
        return new UpdateInventoryUseCase(activityRepository, inventoryRepository, eventProducer);
    }

    @Bean
    public ListAvailableProductsDriverPort listAvailableProductsDriverPort(
            ProductRepository productRepository
    ) {
        return new ListAvailableProductsUseCase(productRepository);
    }

}
