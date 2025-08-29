package com.autostore.user.domain;


import com.autostore.user.domain.exception.ValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


public class CpfTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "529.982.247-25",
            "52998224725",
            "111.444.777-35",
            "1114447773d5",
            "11144477735",
            "123.456.789-09"
    })
    void testShouldCreateValidCpf(String cpfValue) {
        // Act & Assert
        assertDoesNotThrow(() -> new Cpf(cpfValue));

        Cpf cpf = new Cpf(cpfValue);
        assertNotNull(cpf);
        assertEquals(cpfValue.replaceAll("\\D", ""), cpf.value());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123.456.789-00",
            "12345678900",
            "000.000.000-00",
            "00000000000",
            "123",
            "111.111.111-11",
            "11144477733345"})
    @NullAndEmptySource
    void testShouldThrowExceptionForInvalidCpf(String cpfValue) {
        // Act & Assert
        assertThrows(ValidationException.class, () -> new Cpf(cpfValue));
    }
}
