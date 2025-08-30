package com.autostore.user.acceptance.steps;


import com.autostore.user.acceptance.config.CucumberContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@Transactional
public class BaseScenario {


    @Autowired
    protected CucumberContextHolder cucumberContextHolder;

    @Autowired
    protected TestRestTemplate testRestTemplate;

}
