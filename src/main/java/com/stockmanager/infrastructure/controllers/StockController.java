package com.stockmanager.infrastructure.controllers;


import com.stockmanager.domain.stock.StockService;
import com.stockmanager.domain.stock.dto.StockDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/stocks")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    ResponseEntity<StockDto> saveStock(@Valid @RequestBody StockDto stock) {
        StockDto stockDto = stockService.saveStock(stock);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(stockDto);
    }
}
