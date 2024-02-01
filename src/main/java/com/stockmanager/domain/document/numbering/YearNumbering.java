package com.stockmanager.domain.document.numbering;

import com.stockmanager.domain.document.Document;
import com.stockmanager.domain.document.DocumentRepository;
import com.stockmanager.domain.document.DocumentType;
import com.stockmanager.domain.document.dto.DocumentRequestDto;

import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

public class YearNumbering implements NumberingStrategy {
    private final int START_COUNT_FROM = 0;
    private final int NUMBERING_STEP = 1;
    @Override
    public int getNumber(DocumentRepository documentRepository, DocumentType documentType) {
        LocalDate firstDayYear = LocalDate.now()
                .with(firstDayOfYear());
        return documentRepository.findLatestNumber(documentType, firstDayYear)
                .map(Document::getNumber)
                .orElse(START_COUNT_FROM);
    }

    @Override
    public int createNumber(DocumentRepository documentRepository, DocumentRequestDto documentDto) {
        return getNumber(documentRepository, documentDto.getDocumentType())
                + NUMBERING_STEP;
    }
}
