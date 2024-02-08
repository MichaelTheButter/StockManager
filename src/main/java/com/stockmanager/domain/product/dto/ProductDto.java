package com.stockmanager.domain.product.dto;

import com.stockmanager.domain.product.unit.Unit;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(max = 100, message = "name must not exceed 100 characters")
    private String name;
    @NotNull(message = "unit must not be null")
    private Unit unit;
}
