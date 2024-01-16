package com.stockmanager.core.product;

import com.stockmanager.core.product.dto.ProductDto;
import com.stockmanager.core.stockProduct.StockProduct;
import com.stockmanager.core.stockProduct.StockProductDtoMapper;
import com.stockmanager.core.stockProduct.StockProductRepository;
import com.stockmanager.core.stockProduct.dto.StockProductDto;
import com.stockmanager.core.stockProduct.dto.StockProductDtoExtensive;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {
    @Getter
    private final Long MAIN_STOCK_ID = 1L;
    private final int INITIAL_QUANTITY = 0;
    private final ProductRepository productRepository;
    private final StockProductRepository stockProductRepository;
    private final StockProductDtoMapper stockProductDtoMapper;



    public ProductService(ProductRepository productRepository,
                          StockProductRepository stockProductRepository,
                          StockProductDtoMapper stockProductDtoMapper) {
        this.productRepository = productRepository;
        this.stockProductRepository = stockProductRepository;
        this.stockProductDtoMapper = stockProductDtoMapper;
    }

    @Transactional
    public ProductDto saveProduct(ProductDto productDto) {
        Product product = ProductDtoMapper.map(productDto);
        Product savedProduct = productRepository.save(product);
        initialProductAddToMainStock(savedProduct);
        return ProductDtoMapper.map(savedProduct);
    }

    @Transactional
    public List<StockProductDtoExtensive> getStockProducts(Long stockId) {
        return stockProductRepository.findAllById_StockId(stockId)
                .stream()
                .map(stockProductDtoMapper::map)
                .collect(Collectors.toList());

    }
    private void initialProductAddToMainStock(Product product) {
        StockProductDto stockProductDto = new StockProductDto(
                product.getId(),
                MAIN_STOCK_ID,
                INITIAL_QUANTITY
        );
        StockProduct stockProduct = stockProductDtoMapper.map(stockProductDto);
        stockProductRepository.save(stockProduct);
    }
}
