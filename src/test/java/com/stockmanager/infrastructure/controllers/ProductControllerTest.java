package com.stockmanager.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockmanager.domain.product.Product;
import com.stockmanager.domain.product.ProductRepository;
import com.stockmanager.domain.product.dto.ProductDto;
import com.stockmanager.domain.product.unit.Unit;
import com.stockmanager.domain.stockProduct.StockProduct;
import com.stockmanager.domain.stockProduct.StockProductId;
import com.stockmanager.domain.stockProduct.StockProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = {"ADMIN"})
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StockProductRepository stockProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void should_add_product_to_main_stock_with_quantity_0() throws Exception {
        // given
        Long mainStockId = 1L;
        int initialQuantity = 0;
        ProductDto productDto = new ProductDto();
        productDto.setName("testProduct");
        productDto.setUnit(Unit.KILOGRAMS);
        String content = objectMapper.writeValueAsString(productDto);

        // when
        MvcResult mvcResult = mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andDo(print())
                .andReturn();

        //then
        ProductDto savedProduct = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                ProductDto.class);
        StockProductId id = new StockProductId(savedProduct.getId(), mainStockId);
        Optional<StockProduct> stockProduct = stockProductRepository.findById(id);

        assertEquals(productDto.getName(), savedProduct.getName());
        assertEquals(productDto.getUnit(), savedProduct.getUnit());
        assertNotNull(savedProduct.getId());

        assertTrue(stockProduct.isPresent());
        assertEquals(initialQuantity, stockProduct.get().getQuantity());
    }

    @Test
    public void should_get_product() throws Exception {
        //given
        Product product = new Product();
        product.setName("testProduct-2");
        product.setUnit(Unit.KILOGRAMS);
        Long id = productRepository.save(product).getId();
        String url = "/api/products/" + id;

        //when
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().is(200))
                .andDo(print())
                .andReturn();

        //then
        ProductDto productDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ProductDto.class);
        assertNotNull(productDto);
        assertEquals(product.getName(), productDto.getName());
        assertEquals(product.getUnit(), productDto.getUnit());
        assertEquals(id, productDto.getId());
    }

}