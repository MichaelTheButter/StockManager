package com.stockmanager.core.stock;

import com.stockmanager.core.stock.dto.StockDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @Mock
    private StockRepository repo;

    @InjectMocks
    private StockService stockService;

    @Test
    void should_return_stockDto_when_new_stock_is_saved() {
        StockDto stockToSave = new StockDto();
        stockToSave.setName("test stock");
        Stock stock = new Stock();
        stock.setName("test stock");

        //Mockito.when(repo.save(any(Stock.class))).thenReturn(stock);
        Mockito.when(repo.save(any(Stock.class))).then(i -> {
            stock.setId(2L);
            return stock;
        });
        StockDto savedStock = stockService.saveStock(stockToSave);

        assertEquals("test stock", savedStock.getName());
        assertEquals(2L, savedStock.getId());

    }
}