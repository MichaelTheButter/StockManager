package com.stockmanager.domain.stock;

import com.stockmanager.domain.stockProduct.StockProduct;
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
    @Column(unique = true, nullable = false)
    private String name;
    @OneToMany
    @JoinColumn(name = "stock_id")
    private List<StockProduct> products;
}
