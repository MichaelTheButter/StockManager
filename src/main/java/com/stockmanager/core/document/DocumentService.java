package com.stockmanager.core.document;

import com.stockmanager.core.document.dto.DocumentDto;
import com.stockmanager.core.document.dto.DocumentRequestDto;
import com.stockmanager.core.documentProduct.DocumentProduct;
import com.stockmanager.core.documentProduct.DocumentProductService;
import com.stockmanager.core.stockProduct.StockProductDtoMapper;
import com.stockmanager.core.stockProduct.StockProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;
    private final DocumentProductService documentProductService;
    private final StockProductService stockProductService;

    public DocumentService(DocumentRepository documentRepository,
                           DocumentMapper documentMapper,
                           DocumentProductService documentProductService,
                           StockProductService stockProductService
    ) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
        this.documentProductService = documentProductService;
        this.stockProductService = stockProductService;
    }

    @Transactional
    public DocumentDto save(DocumentRequestDto requestDto) {
        Document document = documentMapper.map(requestDto);
        Document savedDocument = documentRepository.save(document);
        Long documentId = savedDocument.getId();
        Long stockId = savedDocument.getStock().getId();
        for(DocumentProduct product : savedDocument.getProducts()) {
            product.setDocumentId(documentId);
            documentProductService.save(product);
            stockProductService.save(
                    StockProductDtoMapper.map(product, stockId)
            );
        }
        return documentMapper.map(savedDocument);
    }
}
