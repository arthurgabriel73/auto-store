Feature: User Registration
  As a system administrator
  I want to register new users
  So that they can use the system

  Scenario: Successfully register a new user
    Given I have a valid user registration form
    When I submit the registration form
    Then I should see the new user id
    And the user should be saved in the database with the correct details

  Scenario: Attempt to register a user with an existing email
    Given I have registered a user with email "johndoe@mail.com"
    And I have a user registration form with email "johndoe@mail.com"
    When I submit the registration form
    Then I should receive a response with status code 409

  Scenario: Attempt to register a user with an existing cpf
    Given I have registered a user with cpf "530.916.230-50"
    And I have a user registration form with cpf "530.916.230-50"
    When I submit the registration form
    Then I should receive a response with status code 409


  Scenario: Attempt to register a user with invalid email format
    Given I have a user registration form with email "invalid-email"
    When I submit the registration form
    Then I should receive a response with status code 400
