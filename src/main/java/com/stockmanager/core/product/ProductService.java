package com.stockmanager.core.product;

import com.stockmanager.core.product.dto.ProductDto;
import com.stockmanager.core.stock.Stock;
import com.stockmanager.core.stock.StockRepository;
import com.stockmanager.core.stockProduct.StockProduct;
import com.stockmanager.core.stockProduct.StockProductDtoMapper;
import com.stockmanager.core.stockProduct.StockProductRepository;
import com.stockmanager.core.stockProduct.dto.StockProductDto;
import com.stockmanager.core.stockProduct.dto.StockProductDtoExtensive;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {
    @Getter
    private final Long MAIN_STOCK_ID = 1L;
    private final int INITIAL_QUANTITY = 0;
    private final ProductRepository productRepository;
    private final StockProductRepository stockProductRepository;
    private final StockRepository stockRepository;


    public ProductService(ProductRepository productRepository, StockProductRepository stockProductRepository,
                          StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.stockProductRepository = stockProductRepository;
        this.stockRepository = stockRepository;
    }


    @Transactional
    public ProductDto saveProduct(ProductDto productDto) {
        Product product = ProductDtoMapper.map(productDto);
        Product savedProduct = productRepository.save(product);
        Long productId = savedProduct.getId();
        StockProductDto stockProductDto = new StockProductDto(productId, MAIN_STOCK_ID, INITIAL_QUANTITY);
        StockProduct stockProduct = StockProductDtoMapper.map(stockProductDto);
        stockProductRepository.save(stockProduct);
        return ProductDtoMapper.map(savedProduct);
    }

    @Transactional
    public List<StockProductDtoExtensive> getStockProducts(Long stockId) {
        return stockProductRepository.findAllById_StockId(stockId)
                .stream()
                .map(this::map)
                .collect(Collectors.toList());

    }

    private StockProductDtoExtensive map(StockProduct stockProduct) {
        Long productId = stockProduct.getId().getProductId();
        Long stockId = stockProduct.getId().getStockId();
        String productName =  productRepository.findById(productId)
                .map(Product::getName)
                .orElse(null);
        String stockName = stockRepository.findById(stockId)
                .map(Stock::getName)
                .orElse(null);
        return StockProductDtoExtensive.builder()
                .productId(productId)
                .stockId(stockId)
                .quantity(stockProduct.getQuantity())
                .productName(productName)
                .stockName(stockName)
                .build();
    }

}
