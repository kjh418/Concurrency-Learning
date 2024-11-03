package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository, StockRepository stockRepository1) {
        this.stockRepository = stockRepository1;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void decrease(Long id, Long quantity){

        Stock stock = stockRepository.findById(id).orElse(new Stock());
        stock.decrease(quantity);
        stockRepository.saveAndFlush(stock);
    }
}
