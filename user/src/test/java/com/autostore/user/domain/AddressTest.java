package com.autostore.user.domain;


import com.autostore.user.domain.exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class AddressTest {

    private static final Long VALID_ID = 1L;
    private static final String VALID_STREET = "Main Street";
    private static final String VALID_NUMBER = "123";
    private static final String VALID_NEIGHBORHOOD = "Downtown";
    private static final String VALID_CITY = "Springfield";
    private static final String VALID_STATE = "IL";
    private static final String VALID_ZIP_CODE = "62701";
    private static final String VALID_COMPLEMENT = "Apt 4B";
    private static final String VALID_COUNTRY = "USA";
    private static final String TOO_LONG_STRING =
            "ThisTooLongStringIsWayTooLongToBeValidBecauseItHas101CHARACTERSAndSoItShouldThrowAValidationException";

    private static Stream<Arguments> invalidValuesProvider() {
        return Stream.of(
                Arguments.of(null, "street"),
                Arguments.of("", "street"),
                Arguments.of(" ", "street"),
                Arguments.of(TOO_LONG_STRING, "street"),
                Arguments.of(null, "number"),
                Arguments.of("", "number"),
                Arguments.of(" ", "number"),
                Arguments.of(TOO_LONG_STRING, "number"),
                Arguments.of(null, "city"),
                Arguments.of("", "city"),
                Arguments.of(" ", "city"),
                Arguments.of(TOO_LONG_STRING, "city"),
                Arguments.of(null, "state"),
                Arguments.of("", "state"),
                Arguments.of(" ", "state"),
                Arguments.of(TOO_LONG_STRING, "state"),
                Arguments.of(null, "country"),
                Arguments.of("", "country"),
                Arguments.of(" ", "country"),
                Arguments.of(TOO_LONG_STRING, "country")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidValuesProvider")
    void testShouldThrowExceptionWhenFieldsAreInvalid(String invalidValue, String fieldName) {
        // Act & Assert
        assertThrows(ValidationException.class, () -> {
            switch (fieldName) {
                case "street" -> new Address(VALID_ID, invalidValue, VALID_NUMBER, VALID_NEIGHBORHOOD,
                        VALID_CITY, VALID_STATE, VALID_ZIP_CODE, VALID_COMPLEMENT, VALID_COUNTRY);
                case "number" -> new Address(VALID_ID, VALID_STREET, invalidValue, VALID_NEIGHBORHOOD,
                        VALID_CITY, VALID_STATE, VALID_ZIP_CODE, VALID_COMPLEMENT, VALID_COUNTRY);
                case "city" -> new Address(VALID_ID, VALID_STREET, VALID_NUMBER, VALID_NEIGHBORHOOD,
                        invalidValue, VALID_STATE, VALID_ZIP_CODE, VALID_COMPLEMENT, VALID_COUNTRY);
                case "state" -> new Address(VALID_ID, VALID_STREET, VALID_NUMBER, VALID_NEIGHBORHOOD,
                        VALID_CITY, invalidValue, VALID_ZIP_CODE, VALID_COMPLEMENT, VALID_COUNTRY);
                case "country" -> new Address(VALID_ID, VALID_STREET, VALID_NUMBER, VALID_NEIGHBORHOOD,
                        VALID_CITY, VALID_STATE, VALID_ZIP_CODE, VALID_COMPLEMENT, invalidValue);
            }
        });
    }

    @Test
    void shouldCreateValidAddress() {
        // Act
        Address address = createValidAddress();

        // Assert
        assertValidAddress(address);
    }

    @Test
    void testShouldCreateAddressWithNullId() {
        // Act & Assert
        Address address = new Address(
                null,
                VALID_STREET,
                VALID_NUMBER,
                VALID_NEIGHBORHOOD,
                VALID_CITY,
                VALID_STATE,
                VALID_ZIP_CODE,
                VALID_COMPLEMENT,
                VALID_COUNTRY
        );

        // Assert
        assertNotNull(address);
        assertNull(address.id());
    }


    private Address createValidAddress() {
        return new Address(VALID_ID, VALID_STREET, VALID_NUMBER, VALID_NEIGHBORHOOD,
                VALID_CITY, VALID_STATE, VALID_ZIP_CODE, VALID_COMPLEMENT, VALID_COUNTRY);
    }

    private void assertValidAddress(Address address) {
        assertAll("Valid address assertions",
                () -> assertNotNull(address),
                () -> assertEquals(VALID_ID, address.id()),
                () -> assertEquals(VALID_STREET, address.street()),
                () -> assertEquals(VALID_NUMBER, address.number()),
                () -> assertEquals(VALID_NEIGHBORHOOD, address.neighborhood()),
                () -> assertEquals(VALID_CITY, address.city()),
                () -> assertEquals(VALID_STATE, address.state()),
                () -> assertEquals(VALID_ZIP_CODE, address.zipCode()),
                () -> assertEquals(VALID_COMPLEMENT, address.complement()),
                () -> assertEquals(VALID_COUNTRY, address.country())
        );
    }

}
