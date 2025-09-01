package com.autostore.inventory.infrastructure.config.ioc;


import com.autostore.inventory.application.port.driven.ActivityRepository;
import com.autostore.inventory.application.port.driven.EventProducer;
import com.autostore.inventory.application.port.driven.InventoryRepository;
import com.autostore.inventory.application.port.driven.ProductRepository;
import com.autostore.inventory.application.port.driver.AddProductStockDriverPort;
import com.autostore.inventory.application.port.driver.RegisterProductDriverPort;
import com.autostore.inventory.application.port.driver.UpdateInventoryDriverPort;
import com.autostore.inventory.application.port.driver.UpdateProductDriverPort;
import com.autostore.inventory.application.usecase.AddProductStockUseCase;
import com.autostore.inventory.application.usecase.RegisterProductUseCase;
import com.autostore.inventory.application.usecase.UpdateInventoryUseCase;
import com.autostore.inventory.application.usecase.UpdateProductUseCase;
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

}
