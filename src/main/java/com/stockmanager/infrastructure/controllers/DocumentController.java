package com.stockmanager.infrastructure.controllers;

import com.stockmanager.core.document.DocumentService;
import com.stockmanager.core.document.dto.DocumentDto;
import com.stockmanager.core.document.dto.DocumentRequestDto;
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

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }
    @PostMapping
    ResponseEntity<DocumentDto> save(@RequestBody DocumentRequestDto requestDto) {
        DocumentDto documentDto = documentService.save(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(documentDto);
    }
}