package com.stockmanager.domain.documentProduct;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "document_id"})}
)
public class DocumentProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "document_id")
    private Long documentId;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = true)
    private double unitPrice;

}
