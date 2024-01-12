package com.stockmanager.domain.stock;

import com.stockmanager.domain.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    private int quantity;
}
