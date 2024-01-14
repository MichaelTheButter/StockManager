package com.stockmanager.core.stockProduct.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockProductDto {
    private Long productId;
    private Long stockId;
    private int quantity;
}
