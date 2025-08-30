Feature: User
  As a system administrator
  I want to register new users
  So that they can use the system and I can manage their information

  Scenario: Successfully register a new user
    Given I have an user registration form with cpf "530.916.230-50" and email "ratyas@mail.com"
    When I submit the registration form
    Then I should receive a response with status code 201
    And I should see the new user id and its name
    And the user with cpf "530.916.230-50" should be saved in the database with the correct details

  Scenario: Attempt to register an user with an existing email
    Given I have registered an user with cpf "225.871.970-40" and email "zouhus@mail.com"
    And I have an user registration form with cpf "718.729.770-06" and email "zouhus@mail.com"
    When I submit the registration form
    Then I should receive a response with status code 409

  Scenario: Attempt to register an user with an existing cpf
    Given I have registered an user with cpf "250.255.280-03" and email "giabir@mail.com"
    And I have an user registration form with cpf "250.255.280-03" and email "sihyor@mail.com"
    When I submit the registration form
    Then I should receive a response with status code 409

  Scenario: Attempt to register an user with invalid email format
    Given I have registered an user with cpf "393.775.940-90" and email "invalid-email"
    And I have an user registration form with cpf "393.775.940-90" and email "invalid-email"
    When I submit the registration form
    Then I should receive a response with status code 400

  Scenario: Attempt to find an user after registration
    Given I have registered an user with cpf "119.557.420-73" and email "elpufo@mail.com"
    When I search for the user by cpf "119.557.420-73"
    Then I should receive a response with status code 200
    And I should find the user by cpf "119.557.420-73" with the correct details

  Scenario: Attempt to find a non-existent user
    When I search for the user by cpf "877.245.980-88"
    Then I should receive a response with status code 404
