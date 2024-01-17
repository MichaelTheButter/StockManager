package com.stockmanager.core.document;

import com.stockmanager.core.document.dto.DocumentDto;
import com.stockmanager.core.document.dto.DocumentRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    public DocumentService(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    @Transactional
    public DocumentDto save(DocumentRequestDto requestDto) {
        Document document = documentMapper.map(requestDto);
        Document savedDocument = documentRepository.save(document);
        return documentMapper.map(savedDocument);
    }
}
