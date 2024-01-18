package com.stockmanager.core.document;

import com.stockmanager.core.document.dto.DocumentDto;
import com.stockmanager.core.document.dto.DocumentRequestDto;
import com.stockmanager.core.documentProduct.DocumentProduct;
import com.stockmanager.core.documentProduct.DocumentProductRepository;
import com.stockmanager.core.stockProduct.StockProductDtoMapper;
import com.stockmanager.core.stockProduct.StockProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;
    private final DocumentProductRepository documentProductRepository;
    private final StockProductRepository stockProductRepository;

    public DocumentService(
            DocumentRepository documentRepository,
            DocumentMapper documentMapper,
            DocumentProductRepository documentProductRepository,
            StockProductRepository stockProductRepository
    ) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
        this.documentProductRepository = documentProductRepository;
        this.stockProductRepository = stockProductRepository;
    }

    @Transactional
    public DocumentDto save(DocumentRequestDto requestDto) {
        Document document = documentMapper.map(requestDto);
        Document savedDocument = documentRepository.save(document);
        Long documentId = savedDocument.getId();
        Long stockId = savedDocument.getStock().getId();
        for(DocumentProduct product : savedDocument.getProducts()) {
            product.setDocumentId(documentId);
            documentProductRepository.save(product);
            stockProductRepository.save(
                    StockProductDtoMapper.map(product, stockId)
            );
        }
        return documentMapper.map(savedDocument);
    }
}
