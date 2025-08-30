package com.autostore.user.acceptance.steps;


import com.autostore.user.acceptance.config.CucumberContextHolder;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Testcontainers
@Transactional
public class BaseScenario {

    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.3")
            .withDatabaseName("user-test-db").withUsername("postgres-test-user").withPassword("postgres-test-password");

    @Autowired
    protected CucumberContextHolder cucumberContextHolder;

    @Autowired
    protected TestRestTemplate testRestTemplate;


    @BeforeAll
    public static void shouldConnectToDatabase() {
        assertTrue(postgres.isCreated());
        assertTrue(postgres.isRunning());
    }

}
