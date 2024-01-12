package com.stockmanager.domain.stock;

import com.stockmanager.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class StockProductId implements Serializable {
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "stock_id")
    private Long stockId;

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getStockId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockProductId)) return false;
        StockProductId that = (StockProductId) o;
        return Objects.equals(getProductId(), that.getProductId()) &&
                Objects.equals(getStockId(), that.getStockId());
    }
}
