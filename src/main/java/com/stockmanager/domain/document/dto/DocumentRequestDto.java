package com.stockmanager.domain.document.dto;

import com.stockmanager.config.enumValidator.constraints.DocumentTypeSubset;
import com.stockmanager.domain.document.DocumentType;
import com.stockmanager.domain.documentProduct.DocumentProduct;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DocumentRequestDto {
    @NotNull(message = "document type must not be null")
    @DocumentTypeSubset(
            anyOf = {DocumentType.GOODS_RECEIVED, DocumentType.INVENTORY, DocumentType.DISPATCH_NOTE}
    )
    private DocumentType documentType;
    @NotNull(message = "stockId must not be null")
    private Long stockId;
    private List<DocumentProduct> products;
}
