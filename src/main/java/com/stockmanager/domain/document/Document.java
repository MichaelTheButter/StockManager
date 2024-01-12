package com.stockmanager.domain.document;

import com.stockmanager.domain.product.Product;
import com.stockmanager.domain.stock.Stock;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    @OneToMany
    @CollectionTable(name = "docs_stock_mapping",
    joinColumns = {@JoinColumn(name = "document_id", referencedColumnName = "id")})
    @MapKey(name = "stock_id")
    @Column(name="quantity")
    private Map<Stock, Integer> stock;

}
