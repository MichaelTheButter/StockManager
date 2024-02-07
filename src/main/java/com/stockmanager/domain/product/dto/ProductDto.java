package com.stockmanager.domain.product.dto;

import com.stockmanager.config.enumValidator.constraints.UnitSubset;
import com.stockmanager.domain.product.unit.Unit;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    @NotNull(message = "name must not be null")
    private String name;
    @NotNull(message = "unit must not be null")
    @UnitSubset(
         anyOf = {Unit.KILOGRAMS, Unit.METER, Unit.MILLIMETERS, Unit.PIECE}
    )
    private Unit unit;
}
