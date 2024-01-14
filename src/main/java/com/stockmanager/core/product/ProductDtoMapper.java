package com.stockmanager.core.product;

import com.stockmanager.core.product.dto.ProductDto;

class ProductDtoMapper {
    static ProductDto map(Product product) {
        return new ProductDto(
                product.getName(),
                product.getUnit()
        );
    }
    static Product map(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setUnit(productDto.getUnit());
        return product;
    }
}
