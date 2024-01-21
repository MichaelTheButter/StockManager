package com.stockmanager.core.stockProduct;

import com.stockmanager.core.documentProduct.DocumentProduct;
import com.stockmanager.core.product.ProductService;
import com.stockmanager.core.product.dto.ProductDto;
import com.stockmanager.core.stock.StockService;
import com.stockmanager.core.stock.dto.StockDto;
import com.stockmanager.core.stockProduct.dto.StockProductDto;
import com.stockmanager.core.stockProduct.dto.StockProductDtoResponse;
import org.springframework.stereotype.Service;

@Service
public class StockProductDtoMapper {
    private final ProductService productService;
    private final StockService stockService;

    public StockProductDtoMapper(ProductService productService, StockService stockService) {
        this.productService = productService;
        this.stockService = stockService;
    }

    StockProduct map(StockProductDto stockProductDto) {
        StockProduct stockProduct = new StockProduct();
        stockProduct.setId(new StockProductId(stockProductDto.getProductId(), stockProductDto.getStockId()));
        stockProduct.setQuantity(stockProductDto.getQuantity());
        return stockProduct;
    }

    StockProductDtoResponse map(StockProduct stockProduct) {
        Long productId = stockProduct.getId().getProductId();
        Long stockId = stockProduct.getId().getStockId();
        String productName =  productService.findById(productId)
                .map(ProductDto::getName)
                .get();
        String stockName = stockService.findByID(stockId)
                .map(StockDto::getName)
                .get();
        return StockProductDtoResponse.builder()
                .productId(productId)
                .stockId(stockId)
                .quantity(stockProduct.getQuantity())
                .productName(productName)
                .stockName(stockName)
                .build();
    }

    public static StockProductDto map(DocumentProduct documentProduct, Long stockId) {
        StockProductDto productToUpdate = new StockProductDto();
        productToUpdate.setProductId(documentProduct.getProductId());
        productToUpdate.setStockId(stockId);
        productToUpdate.setQuantity(documentProduct.getQuantity());
        return productToUpdate;
    }

}

