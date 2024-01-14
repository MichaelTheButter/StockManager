package com.stockmanager.core.stock;

import com.stockmanager.core.stockProduct.StockProduct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @OneToMany
    @JoinColumn(name = "stock_id")
    private List<StockProduct> products;
}
