package com.stockmanager.core.stockProduct.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockProductDto {
    private Long productId;
    private Long stockId;
    private int quantity;
}
