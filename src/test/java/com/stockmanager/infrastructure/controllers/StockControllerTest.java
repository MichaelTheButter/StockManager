package com.stockmanager.infrastructure.controllers;

import com.stockmanager.core.stock.StockService;
import com.stockmanager.core.stock.dto.StockDto;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = {"ADMIN"})
@ExtendWith(SpringExtension.class)
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StockService stockService;
    @Test
    public void shouldCreateNewStock() throws Exception {
        String stock = """ 
                {"name": "testStock"}
                """;
        mockMvc.perform(MockMvcRequestBuilders.post("/api/stocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stock)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2));
    }

    @Test
    public void shouldGetMainStock(){
        Long id = 1L;
        Optional<StockDto> stockDto = stockService.findByID(id);
        assertFalse(stockDto.isEmpty());
        assertEquals("main", stockDto.get().getName());
    }


}