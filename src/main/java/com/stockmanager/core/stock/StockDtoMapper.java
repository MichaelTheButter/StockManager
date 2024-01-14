package com.stockmanager.core.stock;

import com.stockmanager.core.stock.dto.StockDto;

class StockDtoMapper {

    static Stock map(StockDto stockDto) {
        Stock stock = new Stock();
        stock.setName(stockDto.getName());
        return stock;
    }

    static StockDto map(Stock stock) {
        return new StockDto(
                stock.getId(),
                stock.getName()
        );
    }
}
