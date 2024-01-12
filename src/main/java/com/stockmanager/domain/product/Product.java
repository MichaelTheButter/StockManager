package com.stockmanager.domain.product;

import com.stockmanager.domain.stock.StockProdudct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Unit unit;
    @OneToMany
    @JoinColumn(name = "product_id")
    private List<StockProdudct> stocks;

}
