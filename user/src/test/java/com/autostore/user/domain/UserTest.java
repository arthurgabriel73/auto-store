package com.autostore.user.domain;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


public class UserTest {
    private static final FullName fullName = new FullName("John", "Doe");
    private static final Cpf cpf = new Cpf("380.229.590-06");
    private static final Email email = new Email("johndoe123@mail.com");
    private static final Address address = new Address(
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
                .id(1L)
                .fullName(fullName)
                .cpf(cpf)
                .email(email)
                .address(address)
                .createdAt(now)
                .updatedAt(now)
                .build();

        // Assert
        assert user.getId().equals(1L);
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
