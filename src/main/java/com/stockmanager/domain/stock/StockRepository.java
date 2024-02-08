package com.stockmanager.domain.stock;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long>{
    Optional<Stock> findByName(String name);
    boolean existsByName(String name);
}
