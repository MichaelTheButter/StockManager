package com.stockmanager.core.stockProduct;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockProductRepository extends CrudRepository<StockProduct, StockProductId>{
    List<StockProduct> findAllById_StockId(Long id);
}
