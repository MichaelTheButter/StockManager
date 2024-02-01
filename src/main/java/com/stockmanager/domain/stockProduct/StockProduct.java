package com.stockmanager.domain.stockProduct;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class StockProduct {

    @EmbeddedId
    private StockProductId id;
    private int quantity;
}
