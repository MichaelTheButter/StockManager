package com.stockmanager.domain.stock;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class StockProdudct {
    @EmbeddedId
    private StockProductId id;
    private int quantity;
}
