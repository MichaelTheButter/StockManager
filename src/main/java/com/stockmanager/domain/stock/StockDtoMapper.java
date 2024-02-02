package com.stockmanager.domain.stock;

import com.stockmanager.domain.stock.dto.StockDto;

public class StockDtoMapper {

    public static Stock map(StockDto stockDto) {
        Stock stock = new Stock();
        stock.setName(stockDto.getName());
        if (stockDto.getId() != null) stock.setId(stockDto.getId());
        return stock;
    }

    static StockDto map(Stock stock) {
        return new StockDto(
                stock.getId(),
                stock.getName()
        );
    }
}
