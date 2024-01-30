package com.stockmanager.core.document.numbering;

import com.stockmanager.core.document.DocumentRepository;
import com.stockmanager.core.document.DocumentType;
import com.stockmanager.core.document.dto.DocumentRequestDto;

public interface NumberingStrategy {
    int getNumber(DocumentRepository documentRepository, DocumentType documentType);
    int createNumber(DocumentRepository documentRepository, DocumentRequestDto documentDto);
}
