package com.stockmanager.domain.document;

import com.stockmanager.domain.documentProduct.DocumentProduct;
import com.stockmanager.domain.stock.Stock;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "VARCHAR(100)")
    private DocumentType documentType;
    private LocalDate createDate;
    private int number;
    private String name;
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;
    @OneToMany
    @JoinColumn(name = "document_id")
    private List<DocumentProduct> products;
}
