package com.stockmanager.core.product.unit;

import lombok.Getter;

@Getter
public enum Unit {
    PIECE("pc"), METER("m"), MILLIMETERS("mm"), KILOGRAMS("kg");
    private String description;
    Unit(String description) {
        this.description = description;
    }
}
