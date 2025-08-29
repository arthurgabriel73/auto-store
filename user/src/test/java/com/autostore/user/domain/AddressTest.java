package com.autostore.user.domain;


import com.autostore.user.domain.exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


public class AddressTest {

    private static final String TOO_LONG_STRING =
            "ThisTooLongStringIsWayTooLongToBeValidBecauseItHas101CHARACTERSAndSoItShouldThrowAValidationException";

    @Test
    void testShouldCreateValidAddress() {
        // Act & Assert
        Address address = new Address(
                1L,
                "Main Street",
                "123",
                "Downtown",
                "Springfield",
                "IL",
                "62701",
                "Apt 4B",
                "USA"
        );

        // Assert
        assertNotNull(address);
        assertEquals(1L, address.id());
        assertEquals("Main Street", address.street());
        assertEquals("123", address.number());
        assertEquals("Downtown", address.neighborhood());
        assertEquals("Springfield", address.city());
        assertEquals("IL", address.state());
        assertEquals("62701", address.zipCode());
        assertEquals("Apt 4B", address.complement());
        assertEquals("USA", address.country());
    }

    @Test
    void testShouldCreateAddressWithNullId() {
        // Act & Assert
        Address address = new Address(
                null,
                "Main Street",
                "123",
                "Downtown",
                "Springfield",
                "IL",
                "62701",
                "Apt 4B",
                "USA"
        );

        // Assert
        assertNotNull(address);
        assertNull(address.id());
        assertEquals("Main Street", address.street());
        assertEquals("123", address.number());
        assertEquals("Downtown", address.neighborhood());
        assertEquals("Springfield", address.city());
        assertEquals("IL", address.state());
        assertEquals("62701", address.zipCode());
        assertEquals("Apt 4B", address.complement());
        assertEquals("USA", address.country());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "\t", "\n", TOO_LONG_STRING})
    void testShouldThrowExceptionWhenStreetIsInvalid(String invalidStreet) {
        // Act & Assert
        assertThrows(ValidationException.class, () -> new Address(
                1L,
                invalidStreet,
                "123",
                "Downtown",
                "Springfield",
                "IL",
                "62701",
                "Apt 4B",
                "USA"
        ));
    }

}
