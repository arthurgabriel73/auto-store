package com.autostore.user.util;


import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@Component
public class TestUserDataFactory {

    private final Faker faker;

    public TestUserDataFactory() {
        this.faker = new Faker(new Locale("en-US"));
    }

    public Map<String, Object> createUserRegistrationData(String cpf, String email) {
        Map<String, Object> userData = new HashMap<>();
        userData.put("firstName", faker.name().firstName());
        userData.put("lastName", faker.name().lastName());
        userData.put("cpf", cpf);
        userData.put("email", email);
        userData.put("street", faker.address().streetName());
        userData.put("number", faker.address().buildingNumber());
        userData.put("neighborhood", faker.address().secondaryAddress());
        userData.put("city", faker.address().city());
        userData.put("state", faker.address().state());
        userData.put("zipCode", faker.address().zipCode().replace("-", ""));
        userData.put("complement", faker.address().secondaryAddress());
        userData.put("country", faker.address().country());
        return userData;
    }

}
