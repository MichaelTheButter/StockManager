package com.stockmanager.infrastructure.controllers;

import com.stockmanager.core.document.DocumentService;
import com.stockmanager.core.document.dto.DocumentDto;
import com.stockmanager.core.document.dto.DocumentRequestDto;
import com.stockmanager.core.documentProduct.DocumentProduct;
import com.stockmanager.core.documentProduct.DocumentProductMapper;
import com.stockmanager.core.documentProduct.DocumentProductService;
import com.stockmanager.core.documentProduct.dto.DocumentProductDto;
import com.stockmanager.core.stockProduct.StockProductDtoMapper;
import com.stockmanager.core.stockProduct.StockProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/api/documents")
public class DocumentController {
    private final DocumentService documentService;
    private final DocumentProductService documentProductService;

    public DocumentController(DocumentService documentService, DocumentProductService documentProductService) {
        this.documentService = documentService;
        this.documentProductService = documentProductService;
    }

    @Transactional
    @PostMapping
    ResponseEntity<DocumentDto> save(@RequestBody DocumentRequestDto requestDto) {
        DocumentDto documentDto = documentService.save(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(documentDto);
    }
}
