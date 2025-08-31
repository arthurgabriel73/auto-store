package com.autostore.inventory.util;


import com.autostore.inventory.domain.ProductCategory;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@Component
public class TestProductDataFactory {

    private final Faker faker;

    public TestProductDataFactory(Faker faker) {
        this.faker = new Faker(new Locale("en-US"));
    }

    public Map<String, Object> createProductRegistrationData(String code) {
        Map<String, Object> productData = new HashMap<>();
        productData.put("code", code);
        productData.put("unitValue", faker.number().randomDouble(2, 1, 10000));
        productData.put("category", faker.options().option(ProductCategory.class).name());
        productData.put("details", Map.of(
                "description", faker.commerce().productName(),
                "manufacturer", faker.company().name(),
                "model", faker.bothify("Model-??##"),
                "warrantyPeriod", faker.number().numberBetween(6, 36) + " months"
        ));
        return productData;
    }

}
