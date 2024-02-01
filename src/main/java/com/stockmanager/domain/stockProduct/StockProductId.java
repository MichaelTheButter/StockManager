package com.stockmanager.domain.stockProduct;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
