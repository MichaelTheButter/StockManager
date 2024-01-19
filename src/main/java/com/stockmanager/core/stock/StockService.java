package com.stockmanager.core.stock;

import com.stockmanager.core.stock.dto.StockDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public StockDto saveStock(StockDto stockDto) {
        Stock stock = StockDtoMapper.map(stockDto);
        Stock savedStock = stockRepository.save(stock);
        return StockDtoMapper.map(savedStock);
    }
    public Optional<StockDto> findByID(Long id) {
        return stockRepository.findById(id).map(StockDtoMapper::map);
    }
}
