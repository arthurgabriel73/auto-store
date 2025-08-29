package com.autostore.user.domain;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class FullNameTest {

    @ParameterizedTest
    @CsvSource({
            "John,Doe,John Doe",
            "Jane,Smith,Jane Smith",
            "Alice,Johnson,Alice Johnson",
            "Bob,Brown,Bob Brown"
    })
    void testFullNames(String firstName, String lastName, String expectedFullName) {
        // Arrange
        FullName fullName = new FullName(firstName, lastName);

        // Act
        String actualFullName = fullName.completeName();

        // Assert
        assert actualFullName.equals(expectedFullName);
    }
}
