package com.stockmanager.infrastructure.controllers;

import com.stockmanager.core.product.ProductService;
import com.stockmanager.core.product.dto.ProductDto;

import com.stockmanager.core.stockProduct.dto.StockProductDtoExtensive;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;


@Controller
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    ResponseEntity<ProductDto> save(@RequestBody ProductDto product) {
        ProductDto productDto = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productDto);
    }
    @GetMapping
    ResponseEntity<List<StockProductDtoExtensive>> showAllProductsInMainStock() {
        List<StockProductDtoExtensive> products = productService.getStockProducts(productService.getMAIN_STOCK_ID());
        return ResponseEntity.ok(products);
    }

}
