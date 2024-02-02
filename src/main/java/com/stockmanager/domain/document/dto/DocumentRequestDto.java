package com.stockmanager.domain.document.dto;

import com.stockmanager.domain.document.DocumentType;
import com.stockmanager.domain.documentProduct.DocumentProduct;
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
