package com.autostore.user.domain;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AddressTest {

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
}
