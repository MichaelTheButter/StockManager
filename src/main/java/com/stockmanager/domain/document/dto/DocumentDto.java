package com.stockmanager.domain.document.dto;

import com.stockmanager.domain.document.DocumentType;
import com.stockmanager.domain.documentProduct.DocumentProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class DocumentDto {

    private Long id;
    private DocumentType documentType;
    private LocalDate createDate;
    private int number;
    private String name;
    private Long stockId;
    private List<DocumentProduct> products;

}
