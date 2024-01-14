package com.stockmanager.core.stock;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long>{
}
