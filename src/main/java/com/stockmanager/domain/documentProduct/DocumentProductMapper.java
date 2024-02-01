package com.stockmanager.domain.documentProduct;

import com.stockmanager.domain.documentProduct.dto.DocumentProductDto;

public class DocumentProductMapper {
    public static DocumentProduct map(DocumentProductDto dto) {
        DocumentProduct documentProduct = new DocumentProduct();
        documentProduct.setDocumentId(dto.getDocumentId());
        documentProduct.setProductId(dto.getProductId());
        documentProduct.setQuantity(dto.getQuantity());
        documentProduct.setUnitPrice(dto.getUnitPrice());
        return documentProduct;
    }

    public static DocumentProductDto map(DocumentProduct dP){
        return DocumentProductDto.builder()
                .id(dP.getId())
                .documentId(dP.getDocumentId())
                .productId(dP.getProductId())
                .quantity(dP.getQuantity())
                .unitPrice(dP.getUnitPrice())
                .build();
    }
}
