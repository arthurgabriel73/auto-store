package com.autostore.user.acceptance.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;


@Testcontainers
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional
@ActiveProfiles("test")
public class CucumberSpringConfiguration {

    @Container
    static PostgreSQLContainer postgresContainer;

    @BeforeAll
    public static void setup() {
        postgresContainer = new PostgreSQLContainer("postgres:16.3")
                .withDatabaseName("user-test-db")
                .withUsername("postgres-test-user")
                .withPassword("postgres-test-password");
        postgresContainer.start();
        System.out.println(postgresContainer.getJdbcUrl());
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("closing DB connection");
        postgresContainer.stop();
    }


    @TestConfiguration
    static class PostgresTestConfiguration {

        @Bean
        DataSource dataSource() {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(postgresContainer.getJdbcUrl());
            hikariConfig.setUsername(postgresContainer.getUsername());
            hikariConfig.setPassword(postgresContainer.getPassword());
            return new HikariDataSource(hikariConfig);
        }

    }

}
