package com.stockmanager.core.stockProduct;

import com.stockmanager.core.stockProduct.dto.StockProductDto;

public class StockProductDtoMapper {
    public static StockProduct map(StockProductDto stockProductDto) {
        StockProduct stockProduct = new StockProduct();
        stockProduct.setId(new StockProductId(stockProductDto.getProductId(), stockProductDto.getStockId()));
        stockProduct.setQuantity(stockProductDto.getQuantity());
        return stockProduct;
    }

}
