package com.stockmanager.core.stockProduct;

import com.stockmanager.core.documentProduct.DocumentProduct;
import com.stockmanager.core.product.Product;
import com.stockmanager.core.product.ProductRepository;
import com.stockmanager.core.stock.Stock;
import com.stockmanager.core.stock.StockRepository;
import com.stockmanager.core.stockProduct.dto.StockProductDto;
import com.stockmanager.core.stockProduct.dto.StockProductDtoExtensive;
import org.springframework.stereotype.Service;

@Service
public class StockProductDtoMapper {
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    public StockProductDtoMapper(ProductRepository productRepository, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }

    public StockProduct map(StockProductDto stockProductDto) {
        StockProduct stockProduct = new StockProduct();
        stockProduct.setId(new StockProductId(stockProductDto.getProductId(), stockProductDto.getStockId()));
        stockProduct.setQuantity(stockProductDto.getQuantity());
        return stockProduct;
    }

    public StockProductDtoExtensive map(StockProduct stockProduct) {
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

    public static StockProduct map(DocumentProduct documentProduct, Long stockId) {
        StockProduct productToUpdate = new StockProduct();
        productToUpdate.setId(
                new StockProductId(documentProduct.getProductId(), stockId)
        );
        productToUpdate.setQuantity(documentProduct.getQuantity());
        return productToUpdate;
    }

}

