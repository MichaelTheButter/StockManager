package com.stockmanager.core.product;

import com.stockmanager.core.product.unit.Unit;
import com.stockmanager.core.stockProduct.StockProduct;
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

}
