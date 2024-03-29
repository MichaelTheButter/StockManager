package com.stockmanager.domain.document;

import com.stockmanager.domain.document.dto.DocumentDto;
import com.stockmanager.domain.document.dto.DocumentRequestDto;
import com.stockmanager.domain.document.naming.DefaultNaming;
import com.stockmanager.domain.document.naming.NamingStrategy;
import com.stockmanager.domain.document.numbering.NumberingStrategy;
import com.stockmanager.domain.document.numbering.YearNumbering;
import com.stockmanager.domain.stock.Stock;
import com.stockmanager.domain.stock.StockDtoMapper;
import com.stockmanager.domain.stock.StockService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DocumentMapper {
    private final StockService stockService;
    private final DocumentRepository documentRepository;
    @Getter
    @Setter
    private NumberingStrategy numbering = new YearNumbering();
    @Getter
    @Setter
    private NamingStrategy naming = new DefaultNaming();


    public DocumentMapper(StockService stockService, DocumentRepository documentRepository) {
        this.stockService = stockService;
        this.documentRepository = documentRepository;
    }

    Document map(DocumentRequestDto documentDto) {
        Document document = new Document();
        document.setDocumentType(documentDto.getDocumentType());
        document.setStock(getStock(documentDto));
        document.setCreateDate(LocalDate.now());
        int number = numbering.createNumber(documentRepository, documentDto);
        document.setNumber(number);
        String name = naming.createName(document);
        document.setName(name);
        document.setProducts(documentDto.getProducts());
        return document;
    }

    DocumentDto map(Document document) {
        return DocumentDto.builder()
                .id(document.getId())
                .documentType(document.getDocumentType())
                .createDate(document.getCreateDate())
                .number(document.getNumber())
                .name(document.getName())
                .stockId(document.getStock().getId())
                .products(document.getProducts())
                .build();

    }

    private Stock getStock(DocumentRequestDto documentDto) {
        return stockService.findByID(documentDto.getStockId())
                .map(StockDtoMapper::map)
                .get();
    }
}
