package com.stockmanager.core.product.unit;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.*;

import java.util.stream.Stream;

@Converter(autoApply = true)
class UnitConverter implements AttributeConverter<Unit, String> {
    @Override
    public String convertToDatabaseColumn(Unit unit) {
        if(unit == null) return null;
        return unit.getDescription();
    }

    @Override
    public Unit convertToEntityAttribute(String description) {
        if (description == null) return null;
        return Stream.of(Unit.values())
                .filter(unit -> unit.getDescription().equals(description))
                .findFirst()
                .orElse(null);
    }
}
