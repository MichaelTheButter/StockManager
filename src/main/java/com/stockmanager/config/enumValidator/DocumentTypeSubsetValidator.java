package com.stockmanager.config.enumValidator;

import com.stockmanager.config.enumValidator.constraints.DocumentTypeSubset;
import com.stockmanager.domain.document.DocumentType;

public class DocumentTypeSubsetValidator extends EnumSubSetValidator<DocumentTypeSubset, DocumentType> {

    @Override
    public void initialize(DocumentTypeSubset constraint) {
        super.initialize(constraint.anyOf());
    }
}
