package com.stockmanager.core.product.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UnitConverterTest {

    private final UnitConverter unitConverter= new UnitConverter();
    @ParameterizedTest
    @EnumSource(Unit.class)
    void shouldGetDescription(Unit unit) {
        String actual = unitConverter.convertToDatabaseColumn(unit);
        assertEquals(unit.getDescription(), actual);
    }

    @Test
    void shouldReturnNull() {
        //gives
        String wrongInput = "wrong";
        //when
        Unit unit = unitConverter.convertToEntityAttribute(wrongInput);
        assertNull(unit);
    }
}