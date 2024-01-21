package com.stockmanager.core.document;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class DocumentTypeConverter implements AttributeConverter<DocumentType, String>{
    @Override
    public String convertToDatabaseColumn(DocumentType documentType) {
        if (documentType == null) return null;
        return documentType.getAcronym();
    }

    @Override
    public DocumentType convertToEntityAttribute(String description) {
        if (description == null) return null;
        return Stream.of(DocumentType.values())
                .filter(docType -> docType.getAcronym().equals(description))
                .findFirst()
                .orElse(null);
    }
}
