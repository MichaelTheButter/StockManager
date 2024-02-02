package com.stockmanager.domain.stockProduct.dto;

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
