package com.stockmanager.domain.product;

import com.stockmanager.domain.product.dto.ProductDto;
import com.stockmanager.infrastructure.controllers.exceptionhandling.UniqueConstraintException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductDto saveProduct(ProductDto productDto) {
        checkIfProductNameExist(productDto.getName());
        Product product = ProductDtoMapper.map(productDto);
        Product savedProduct = productRepository.save(product);
        return ProductDtoMapper.map(savedProduct);
    }

    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id).map(ProductDtoMapper::map);
    }

    private void checkIfProductNameExist(String name) {
        if (productRepository.existsByName(name)) {
            throw new UniqueConstraintException("Product with given name already exist");
        }
    }
}
