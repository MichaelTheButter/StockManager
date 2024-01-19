package com.stockmanager.core.stockProduct;

import com.stockmanager.core.stockProduct.dto.StockProductDto;
import com.stockmanager.core.stockProduct.dto.StockProductDtoResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockProductService {
    private final StockProductRepository stockProductRepository;
    private final StockProductDtoMapper stockProductDtoMapper;

    public StockProductService(StockProductRepository stockProductRepository, StockProductDtoMapper stockProductDtoMapper) {
        this.stockProductRepository = stockProductRepository;
        this.stockProductDtoMapper = stockProductDtoMapper;
    }

    public StockProductDtoResponse save(StockProductDto stockProductDto) {
        StockProduct stockProduct = stockProductDtoMapper.map(stockProductDto);
        StockProduct savedStockProduct = stockProductRepository.save(stockProduct);
        return stockProductDtoMapper.map(savedStockProduct);

    }

    public List<StockProductDtoResponse> getStockProducts(Long stockId) {
        return stockProductRepository.findAllById_StockId(stockId).stream()
                .map(stockProductDtoMapper::map)
                .collect(Collectors.toList());
    }
}
