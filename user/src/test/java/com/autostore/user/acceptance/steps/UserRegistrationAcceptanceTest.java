package com.autostore.user.acceptance.steps;


import com.autostore.user.application.port.driven.UserRepository;
import com.autostore.user.domain.Cpf;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@Transactional
public class UserRegistrationAcceptanceTest extends BaseScenario {

    private final String BASE_URL = "http://localhost:8080";
    private final String USERS_URI = "/api/v1/user";

    @Autowired
    private UserRepository userRepository;

    @Given("I have a valid user registration form")
    public void i_have_a_valid_user_registration_form() {
        cucumberContextHolder.setRequestData(
                Map.ofEntries(
                        Map.entry("firstName", "John"),
                        Map.entry("lastName", "Doe"),
                        Map.entry("cpf", "047.797.980-78"),
                        Map.entry("email", "john@mail.com"),
                        Map.entry("street", "Main St"),
                        Map.entry("number", "123"),
                        Map.entry("neighborhood", "Downtown"),
                        Map.entry("city", "Metropolis"),
                        Map.entry("state", "State"),
                        Map.entry("zipCode", "12345"),
                        Map.entry("complement", "Apt 1"),
                        Map.entry("country", "Country")
                )
        );
    }

    @Given("I have registered a user with email {string}")
    public void i_have_registered_a_user_with_email(String email) {
        var requestData = Map.ofEntries(
                Map.entry("firstName", "John"),
                Map.entry("lastName", "Doe"),
                Map.entry("cpf", "047.797.980-78"),
                Map.entry("email", email),
                Map.entry("street", "Main St"),
                Map.entry("number", "123"),
                Map.entry("neighborhood", "Downtown"),
                Map.entry("city", "Metropolis"),
                Map.entry("state", "State"),
                Map.entry("zipCode", "12345"),
                Map.entry("complement", "Apt 1"),
                Map.entry("country", "Country")
        );
        testRestTemplate.postForEntity(
                BASE_URL + USERS_URI,
                requestData,
                String.class
        );
    }

    @Given("I have registered a user with cpf {string}")
    public void i_have_registered_a_user_with_cpf(String cpf) {
        var requestData = Map.ofEntries(
                Map.entry("firstName", "John"),
                Map.entry("lastName", "Doe"),
                Map.entry("cpf", cpf),
                Map.entry("email", "john@mail.com"),
                Map.entry("street", "Main St"),
                Map.entry("number", "123"),
                Map.entry("neighborhood", "Downtown"),
                Map.entry("city", "Metropolis"),
                Map.entry("state", "State"),
                Map.entry("zipCode", "12345"),
                Map.entry("complement", "Apt 1"),
                Map.entry("country", "Country")
        );
        testRestTemplate.postForEntity(
                BASE_URL + USERS_URI,
                requestData,
                String.class
        );
    }


    @Given("I have a user registration form with email {string}")
    public void i_have_a_user_registration_form_with_email(String email) {
        cucumberContextHolder.setRequestData(Map.ofEntries(
                Map.entry("firstName", "John"),
                Map.entry("lastName", "Doe"),
                Map.entry("cpf", "047.797.980-78"),
                Map.entry("email", email),
                Map.entry("street", "Main St"),
                Map.entry("number", "123"),
                Map.entry("neighborhood", "Downtown"),
                Map.entry("city", "Metropolis"),
                Map.entry("state", "State"),
                Map.entry("zipCode", "12345"),
                Map.entry("complement", "Apt 1"),
                Map.entry("country", "Country")
        ));
    }

    @Given("I have a user registration form with cpf {string}")
    public void i_have_a_user_registration_form_with_cpf(String cpf) {
        cucumberContextHolder.setRequestData(Map.ofEntries(
                Map.entry("firstName", "John"),
                Map.entry("lastName", "Doe"),
                Map.entry("cpf", cpf),
                Map.entry("email", "john@mail.com"),
                Map.entry("street", "Main St"),
                Map.entry("number", "123"),
                Map.entry("neighborhood", "Downtown"),
                Map.entry("city", "Metropolis"),
                Map.entry("state", "State"),
                Map.entry("zipCode", "12345"),
                Map.entry("complement", "Apt 1"),
                Map.entry("country", "Country")
        ));
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

    @Then("I should see the new user id")
    public void i_should_see_the_new_user_id() {
        var response = cucumberContextHolder.getResponse();
        assertNotNull(response);
        var userId = response.getBody();
        assertNotNull(userId);
    }

    @And("the user should be saved in the database with the correct details")
    public void the_user_should_be_saved_in_the_database_with_correct_details() {
        var response = cucumberContextHolder.getResponse();
        assertNotNull(response);

        var user = userRepository.findByCpf(Cpf.of("047.797.980-78")).orElse(null);
        assertNotNull(user);
        assertNotNull(user.getCpf());
        assertNotNull(user.getEmail());
    }

}
