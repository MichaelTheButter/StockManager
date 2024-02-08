package com.stockmanager.domain.document;

import com.stockmanager.domain.document.dto.DocumentDto;
import com.stockmanager.domain.document.dto.DocumentRequestDto;
import com.stockmanager.domain.documentProduct.DocumentProduct;
import com.stockmanager.domain.stockProduct.StockProductService;
import com.stockmanager.domain.stockProduct.dto.StockProductDtoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class DocumentServiceTest {
    @Autowired
    DocumentService documentService;
    @Autowired
    StockProductService stockProductService;

    @Test
    public void shouldCreateNewDocument_AndIncreaseProductQuantityInStock() {
        //given
        Long productId = 1L;
        Long stockId = 1L;
        int quantity = 10;

        DocumentProduct product = new DocumentProduct();
        product.setProductId(productId);
        product.setQuantity(quantity);
        product.setUnitPrice(2.5);
        List<DocumentProduct> products = List.of(product);

        DocumentRequestDto documentRequestDto = DocumentRequestDto.builder()
                .documentType(DocumentType.GOODS_RECEIVED)
                .stockId(stockId)
                .products(products)
                .build();
        int year = LocalDate.now().getYear();
        String name = "GR/1/" + year;
        //when
        DocumentDto documentDto = documentService.save(documentRequestDto);

        //then
        assertEquals(name, documentDto.getName());
        assertEquals(documentRequestDto.getDocumentType(), documentDto.getDocumentType());
        assertEquals(documentRequestDto.getProducts(), documentDto.getProducts());

        StockProductDtoResponse stockProductDtoResponse = stockProductService.getStockProducts(stockId)
                .stream()
                .filter(stockProd -> stockProd.getProductId() == productId)
                .findFirst()
                .get();

        assertEquals(quantity, stockProductDtoResponse.getQuantity());

    }
}