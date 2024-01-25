package com.stockmanager.core.stockProduct.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class StockProductDto {
    private Long productId;
    private Long stockId;
    private int quantity;
}
