package com.stockmanager.core.document;

import com.stockmanager.core.document.dto.DocumentRequestDto;
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


    public DocumentMapper(StockRepository stockRepository, DocumentRepository documentRepository) {
        this.stockRepository = stockRepository;
        this.documentRepository = documentRepository;
    }

    Document map(DocumentRequestDto documentDto) {
        Document document = new Document();
        document.setDocumentType(documentDto.getDocumentType());
        document.setProducts(documentDto.getProducts());
        document.setStock(getStock(documentDto));
        document.setCreateDate(LocalDate.now());
        int number = numbering.createNumber(documentRepository, documentDto);
        document.setNumber(number);

        return document;
    }

    private Stock getStock(DocumentRequestDto documentDto) {
        return stockRepository.findById(documentDto.getStockId())
                .orElseThrow(IllegalArgumentException::new);
    }
}
