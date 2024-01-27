package com.stockmanager.core.product.dto;

import com.stockmanager.core.product.unit.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Unit unit;
}