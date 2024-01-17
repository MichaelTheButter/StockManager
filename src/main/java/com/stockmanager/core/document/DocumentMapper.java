package com.stockmanager.core.document;

import com.stockmanager.core.document.dto.DocumentDto;
import com.stockmanager.core.document.dto.DocumentRequestDto;
import com.stockmanager.core.document.naming.DefaultNaming;
import com.stockmanager.core.document.naming.NamingStrategy;
import com.stockmanager.core.document.numbering.NumberingStrategy;
import com.stockmanager.core.document.numbering.YearNumbering;
import com.stockmanager.core.stock.Stock;
import com.stockmanager.core.stock.StockRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DocumentMapper {
    private final StockRepository stockRepository;
    private final DocumentRepository documentRepository;
    @Getter
    @Setter
    private NumberingStrategy numbering = new YearNumbering();
    @Getter
    @Setter
    private NamingStrategy naming = new DefaultNaming();


    public DocumentMapper(StockRepository stockRepository, DocumentRepository documentRepository) {
        this.stockRepository = stockRepository;
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
        return stockRepository.findById(documentDto.getStockId())
                .orElseThrow(IllegalArgumentException::new);
    }
}
