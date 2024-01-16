package com.stockmanager.core.document.dto;

import com.stockmanager.core.document.DocumentType;
import com.stockmanager.core.documentProduct.DocumentProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DocumentRequestDto {
    private DocumentType documentType;
    private Long stockId;
    private List<DocumentProduct> products;
}
