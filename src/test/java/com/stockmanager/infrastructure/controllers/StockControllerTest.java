package com.stockmanager.infrastructure.controllers;

import com.stockmanager.core.stock.StockService;
import com.stockmanager.core.stock.dto.StockDto;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = {"ADMIN"})
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StockService stockService;
    @Test
    public void shouldCreateNewStock() throws Exception {
        // given
        String stock = """ 
                {"name": "testStock"}
                """;
        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/stocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stock)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andReturn();
        // then
        StockDto stockDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), StockDto.class);
        assertNotNull(stockDto);
        assertEquals(2L, stockDto.getId());
        assertEquals("testStock", stockDto.getName());

    }

    @Test
    public void shouldGetMainStock(){
        Long id = 1L;
        Optional<StockDto> stockDto = stockService.findByID(id);
        assertFalse(stockDto.isEmpty());
        assertEquals("main", stockDto.get().getName());
    }


}