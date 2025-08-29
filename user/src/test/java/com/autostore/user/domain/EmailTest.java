package com.autostore.user.domain;


import com.autostore.user.domain.exception.ValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


public class EmailTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "user@example.com",
            "first.last@example.com",
            "user@sub.domain.com",
            "user.name+tag@example.com",
            "user@example.co.uk",
            "user@example.io"
    })
    void testShouldCreateValidEmail(String emailValue) {
        // Act & Assert
        assertDoesNotThrow(() -> new Email(emailValue));

        Email email = new Email(emailValue);
        assertNotNull(email);
        assertEquals(emailValue, email.value());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "invalid-email",
            "user@",
            "@example.com",
            "user@.com",
            "user@example..com",
            "user@example.c",
            "user@example.com."
    })
    @NullAndEmptySource
    void testShouldThrowExceptionForInvalidEmail(String invalidEmail) {
        // Act & Assert
        assertThrows(ValidationException.class, () -> new Email(invalidEmail));
    }
}
