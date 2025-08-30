package com.autostore.user.acceptance.steps;


import com.autostore.user.application.port.driven.UserRepository;
import com.autostore.user.application.port.driver.model.command.RegisterUserCommandOutput;
import com.autostore.user.domain.Cpf;
import com.autostore.user.infrastructure.adapter.util.JsonUtil;
import com.autostore.user.util.TestUserDataFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Transactional
public class UserRegistrationAcceptanceTest extends BaseScenario {

    private final String BASE_URL = "http://localhost:8080";
    private final String USERS_URI = "/api/v1/user";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestUserDataFactory testUserDataFactory;

    @Autowired
    private JsonUtil jsonUtil;

    @Given("I have an user registration form with cpf {string} and email {string}")
    public void i_have_an_user_registration_form(String cpf, String email) {
        Map<String, Object> requestData = testUserDataFactory.createUserRegistrationData(cpf, email);
        cucumberContextHolder.setRequestData(requestData);
    }

    @Given("I have registered an user with cpf {string} and email {string}")
    public void i_have_registered_a_user_with_cpf_and_email(String cpf, String email) {
        Map<String, Object> requestData = testUserDataFactory.createUserRegistrationData(cpf, email);
        testRestTemplate.postForEntity(
                BASE_URL + USERS_URI,
                requestData,
                String.class
        );
    }


    @When("I submit the registration form")
    public void i_submit_the_registration_form() {
        var response = testRestTemplate.postForEntity(
                BASE_URL + USERS_URI,
                cucumberContextHolder.getRequestData(),
                String.class
        );
        cucumberContextHolder.setResponse(response);
    }

    @When("I search for the user by cpf {string}")
    public void i_search_for_the_user_by_cpf(String cpf) {
        var response = testRestTemplate.getForEntity(
                BASE_URL + USERS_URI + "/cpf/" + cpf,
                String.class
        );
        cucumberContextHolder.setResponse(response);
    }

    @Then("I should see the new user id and its name")
    public void i_should_see_the_new_user_id_and_its_name() throws Exception {
        var response = cucumberContextHolder.getResponse();
        assertNotNull(response);
        var body = response.getBody();
        assertNotNull(body);

        RegisterUserCommandOutput output = jsonUtil.fromJson(body, RegisterUserCommandOutput.class);

        assertNotNull(output.userId());
        assertDoesNotThrow(() -> UUID.fromString(output.userId()));
    }

    @And("the user with cpf {string} should be saved in the database with the correct details")
    public void the_user_should_be_saved_in_the_database_with_correct_details(String cpf) {
        var response = cucumberContextHolder.getResponse();
        assertNotNull(response);

        var user = userRepository.findByCpf(Cpf.of(cpf)).orElse(null);
        assertNotNull(user);
        assertNotNull(user.getCpf());
        assertNotNull(user.getEmail());
    }

    @Then("I should find the user by cpf {string} with the correct details")
    public void i_should_find_the_user_with_the_correct_details(String cpf) {
        var response = cucumberContextHolder.getResponse();
        assertNotNull(response);

        var user = userRepository.findByCpf(Cpf.of(cpf)).orElse(null);
        assertNotNull(user);
        assertNotNull(user.getCpf());
        assertNotNull(user.getEmail());
    }

}
