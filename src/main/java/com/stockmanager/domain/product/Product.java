package com.stockmanager.domain.product;

import com.stockmanager.domain.documentProduct.DocumentProduct;
import com.stockmanager.domain.product.unit.Unit;
import com.stockmanager.domain.stockProduct.StockProduct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(columnDefinition = "VARCHAR(100)")
    private Unit unit;
    @OneToMany
    @JoinColumn(name = "product_id")
    private List<StockProduct> stocks;
    @OneToMany
    @JoinColumn(name = "product_id")
    private List<DocumentProduct> documents;

}
