package com.autostore.user.acceptance.steps;


import com.autostore.user.acceptance.config.CucumberContextHolder;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class CommonSteps {


    @Autowired
    private CucumberContextHolder cucumberContext;

    @Then("I should receive a response with status code {int}")
    public void i_should_receive_a_response_with_status_code(Integer statusCode) {
        assertNotNull(cucumberContext.getResponse());
        assertEquals(cucumberContext.getResponse().getStatusCode().value(), statusCode);
    }

}
