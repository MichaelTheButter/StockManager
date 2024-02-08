package com.stockmanager.domain.stock;

import com.stockmanager.domain.stock.dto.StockDto;
import com.stockmanager.infrastructure.controllers.exceptionhandling.UniqueConstraintException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public StockDto saveStock(StockDto stockDto) {
        checkIfStocknameExist(stockDto.getName());
        Stock stock = StockDtoMapper.map(stockDto);
        Stock savedStock = stockRepository.save(stock);
        return StockDtoMapper.map(savedStock);
    }
    public Optional<StockDto> findByID(Long id) {
        return stockRepository.findById(id).map(StockDtoMapper::map);
    }

    private void checkIfStocknameExist(String name) {
        if (stockRepository.existsByName(name)){
            throw new UniqueConstraintException("Stock with given name already exist");
        }
    }
}
