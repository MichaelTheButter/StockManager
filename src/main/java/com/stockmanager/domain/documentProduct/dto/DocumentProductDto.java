package com.stockmanager.domain.documentProduct.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DocumentProductDto {
    private Long id;
    private Long productId;
    private Long documentId;
    private int quantity;
    private double unitPrice;
}
