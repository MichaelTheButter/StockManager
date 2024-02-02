package com.stockmanager.infrastructure.controllers;

import com.stockmanager.domain.product.Product;
import com.stockmanager.domain.product.ProductDtoMapper;
import com.stockmanager.domain.product.ProductService;
import com.stockmanager.domain.product.dto.ProductDto;
import com.stockmanager.domain.stockProduct.StockProductService;
import com.stockmanager.domain.stockProduct.dto.StockProductDto;
import com.stockmanager.domain.stockProduct.dto.StockProductDtoResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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

    private void initialProductAddToMainStock(Product product) {
        StockProductDto stockProductDto = new StockProductDto(
                product.getId(),
                MAIN_STOCK_ID,
                INITIAL_QUANTITY
        );
        stockProductService.save(stockProductDto);
    }

    @GetMapping
    ResponseEntity<List<StockProductDtoResponse>> showAllProductsInMainStock() {
        List<StockProductDtoResponse> products = stockProductService.getStockProducts(MAIN_STOCK_ID);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}/stock")
    ResponseEntity<List<StockProductDtoResponse>> shawAllProductInStockById(@PathVariable Long id) {
        List<StockProductDtoResponse> products = stockProductService.getStockProducts(id);
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductDto> showProduct(@PathVariable Long id) {
        Optional<ProductDto> product = productService.findById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
