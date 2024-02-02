package com.stockmanager.domain.documentProduct;

import com.stockmanager.domain.documentProduct.dto.DocumentProductDto;
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
