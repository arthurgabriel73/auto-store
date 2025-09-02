package com.autostore.bff.infrastructure.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
        tags = {
                @Tag(name = "Customer", description = "Customer operations and authentication"),
                @Tag(name = "Product", description = "Product inventory and details"),
                @Tag(name = "Validation", description = "Validate a product to be sold"),
                @Tag(name = "Order", description = "Order creation and tracking")
        }
)
public class OpenApiConfig {

}
