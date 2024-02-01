package com.stockmanager.infrastructure.controllers;

import com.stockmanager.domain.document.DocumentService;
import com.stockmanager.domain.document.dto.DocumentDto;
import com.stockmanager.domain.document.dto.DocumentRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/documents")
public class DocumentController {
    private final DocumentService documentService;


    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Transactional
    @PostMapping
    ResponseEntity<DocumentDto> save(@RequestBody DocumentRequestDto requestDto) {
        DocumentDto documentDto = documentService.save(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(documentDto);
    }

    @GetMapping("/{id}")
    ResponseEntity<DocumentDto> showDocument(@PathVariable Long id) {
        Optional<DocumentDto> document = documentService.findById(id);
        return document.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
