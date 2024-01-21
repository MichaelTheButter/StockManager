package com.stockmanager.infrastructure.controllers;

import com.stockmanager.core.product.Product;
import com.stockmanager.core.product.ProductDtoMapper;
import com.stockmanager.core.product.ProductService;
import com.stockmanager.core.product.dto.ProductDto;
import com.stockmanager.core.stockProduct.StockProductService;
import com.stockmanager.core.stockProduct.dto.StockProductDto;
import com.stockmanager.core.stockProduct.dto.StockProductDtoResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/api/products")
public class ProductController {
    @Getter
    private final Long MAIN_STOCK_ID = 1L;
    private final int INITIAL_QUANTITY = 0;
    private final ProductService productService;
    private final StockProductService stockProductService;

    public ProductController(ProductService productService, StockProductService stockProductService) {
        this.productService = productService;
        this.stockProductService = stockProductService;
    }

    @Transactional
    @PostMapping
    ResponseEntity<ProductDto> save(@RequestBody ProductDto product) {
        ProductDto productDto = productService.saveProduct(product);
        initialProductAddToMainStock(ProductDtoMapper.map(productDto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productDto);
    }
    @GetMapping
    ResponseEntity<List<StockProductDtoResponse>> showAllProductsInMainStock() {
        List<StockProductDtoResponse> products = stockProductService.getStockProducts(MAIN_STOCK_ID);
        return ResponseEntity.ok(products);
    }
    private void initialProductAddToMainStock(Product product) {
        StockProductDto stockProductDto = new StockProductDto(
                product.getId(),
                MAIN_STOCK_ID,
                INITIAL_QUANTITY
        );
        stockProductService.save(stockProductDto);
    }

}
