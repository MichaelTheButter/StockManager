package com.stockmanager.domain.document.numbering;

import com.stockmanager.domain.document.DocumentRepository;
import com.stockmanager.domain.document.DocumentType;
import com.stockmanager.domain.document.dto.DocumentRequestDto;

public interface NumberingStrategy {
    int getNumber(DocumentRepository documentRepository, DocumentType documentType);
    int createNumber(DocumentRepository documentRepository, DocumentRequestDto documentDto);
}
