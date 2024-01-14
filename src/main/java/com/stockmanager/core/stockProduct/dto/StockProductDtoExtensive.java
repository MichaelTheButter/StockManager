package com.stockmanager.core.stockProduct.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockProductDtoExtensive {
    private Long productId;
    private Long stockId;
    private int quantity;
    private String productName;
    private String stockName;
}
