package com.stockmanager.domain.stockProduct.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockProductDto {
    private Long productId;
    private Long stockId;
    private int quantity;
}
