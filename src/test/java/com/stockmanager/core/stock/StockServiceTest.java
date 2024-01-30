package com.stockmanager.core.stock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @Mock
    private StockRepository repo;

    @InjectMocks
    private StockService stockService;

    @Test
    void should_return_stockDto_when_new_stock_is_saved() {
    }
}