package com.autostore.user.domain;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


public class UserTest {

    private static final UserId userId = UserId.generateNew();
    private static final FullName fullName = FullName.of("John", "Doe");
    private static final Cpf cpf = Cpf.of("380.229.590-06");
    private static final Email email = Email.of("johndoe123@mail.com");
    private static final Address address = Address.of(
            1L,
            "Main St",
            "123",
            "Downtown",
            "Metropolis",
            "State",
            "12345",
            "Apt 1",
            "Country"
    );

    @Test
    void testShouldCreateUserSuccessfully() {
        // Arrange & Act
        var now = LocalDateTime.now();
        User user = User.builder()
                .id(userId)
                .fullName(fullName)
                .cpf(cpf)
                .email(email)
                .address(address)
                .createdAt(now)
                .updatedAt(now)
                .build();

        // Assert
        assert user.getId().equals(userId);
        assert user.getFullName().equals(fullName);
        assert user.getCpf().equals(cpf);
        assert user.getEmail().equals(email);
        assert user.getAddress().equals(address);
        assert user.getCreatedAt().equals(now);
        assert user.getUpdatedAt().equals(now);
    }

    @Test
    void testShouldCreateUserWithNullId() {
        // Arrange & Act
        var now = LocalDateTime.now();
        User user = User.builder()
                .fullName(fullName)
                .cpf(cpf)
                .email(email)
                .address(address)
                .createdAt(now)
                .updatedAt(now)
                .build();

        // Assert
        assert user.getId() == null;
        assert user.getFullName().equals(fullName);
        assert user.getCpf().equals(cpf);
        assert user.getEmail().equals(email);
        assert user.getAddress().equals(address);
        assert user.getCreatedAt().equals(now);
        assert user.getUpdatedAt().equals(now);
    }

}
