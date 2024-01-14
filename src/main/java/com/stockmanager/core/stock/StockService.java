package com.stockmanager.core.stock;

import com.stockmanager.core.stock.dto.StockDto;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public StockDto saveStock(StockDto stockDto) {
        Stock stock = StockDtoMapper.map(stockDto);
        Stock savedStock = stockRepository.save(stock);
        return StockDtoMapper.map(savedStock);
    }
}
