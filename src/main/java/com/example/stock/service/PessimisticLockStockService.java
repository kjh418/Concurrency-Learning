package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessimisticLockStockService {

    private final StockRepository stockRepository;

    public PessimisticLockStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void decrease(Long id, Long quantity){
        // lock 가져오기
        Stock stock = stockRepository.findByIdPessimisticLock(id);

        // 재고 감소
        stock.decrease(quantity);

        // 데이터 저장
        stockRepository.save(stock);
    }
}
