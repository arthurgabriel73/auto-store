package com.autostore.user.domain;


import com.autostore.user.domain.exception.ValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class FullNameTest {

    private static final String TOO_LONG_NAME =
            "ThisTooLongNameIsWayTooLongToBeValidBecauseItHas101CHARACTERSAndSoItShouldThrowAValidationExceptionnn";

    @ParameterizedTest
    @CsvSource({
            "John,Doe,John Doe",
            "Jane,Smith,Jane Smith",
            "Alice,Johnson,Alice Johnson",
            "Bob,Brown,Bob Brown"
    })
    void testFullNames(String firstName, String lastName, String expectedFullName) {
        // Arrange
        FullName fullName = FullName.of(firstName, lastName);

        // Act
        String actualFullName = fullName.completeName();

        // Assert
        assert actualFullName.equals(expectedFullName);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "\t", "\n", "A", "a", "1", TOO_LONG_NAME})
    void testShouldThrowExceptionWhenFirstNameIsInvalid(String invalidFirstName) {
        // Act & Assert
        assertThrows(ValidationException.class, () -> FullName.of(invalidFirstName, "Doe"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "\t", "\n", "A", "a", "1", TOO_LONG_NAME})
    void testShouldThrowExceptionWhenLastNameIsInvalid(String invalidLastName) {
        // Act & Assert
        assertThrows(ValidationException.class, () -> FullName.of("John", invalidLastName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"John123", "Maria@", "João#Silva"})
    void testShouldThrowExceptionWhenFirstNameContainsSpecialCharacters(String invalidFirstName) {
        // Act & Assert
        assertThrows(ValidationException.class, () -> FullName.of(invalidFirstName, "Doe"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"John123", "Maria@", "João#Silva"})
    void testShouldThrowExceptionWhenLastNameContainsSpecialCharacters(String invalidLastName) {
        // Act & Assert
        assertThrows(ValidationException.class, () -> FullName.of("John", invalidLastName));
    }

}
