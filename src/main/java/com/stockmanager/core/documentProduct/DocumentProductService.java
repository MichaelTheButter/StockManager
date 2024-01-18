package com.stockmanager.core.documentProduct;

import com.stockmanager.core.documentProduct.dto.DocumentProductDto;
import com.stockmanager.core.stockProduct.StockProductDtoMapper;
import com.stockmanager.core.stockProduct.StockProductRepository;
import org.springframework.stereotype.Service;

@Service
public class DocumentProductService {
    private final DocumentProductRepository documentProductRepository;


    public DocumentProductService(DocumentProductRepository documentProductRepository) {
        this.documentProductRepository = documentProductRepository;
    }

    public DocumentProductDto save(DocumentProductDto dto) {
        DocumentProduct documentProduct = DocumentProductMapper.map(dto);
        return save(documentProduct);
    }

    public DocumentProductDto save(DocumentProduct documentProduct) {
        DocumentProduct savedDocProduct = documentProductRepository.save(documentProduct);
        return DocumentProductMapper.map(savedDocProduct);
    }
}
