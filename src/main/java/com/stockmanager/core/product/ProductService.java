package com.stockmanager.core.product;

import com.stockmanager.core.product.dto.ProductDto;
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
        Product product = ProductDtoMapper.map(productDto);
        Product savedProduct = productRepository.save(product);
        return ProductDtoMapper.map(savedProduct);
    }

    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id).map(ProductDtoMapper::map);
    }

}
