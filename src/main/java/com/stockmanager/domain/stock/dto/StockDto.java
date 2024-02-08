package com.stockmanager.domain.stock.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@AllArgsConstructor
@Getter
@Setter
public class StockDto {
    private Long id;
    @NotNull(message = "name must not be null")
    @Size(max = 100, message = "name must not exceed 100 characters")
    private String name;

}

