package com.eureka.stockAnalytics.repository.stocks;

import com.eureka.stockAnalytics.entity.stocks.PriceHistoryKey;
import com.eureka.stockAnalytics.entity.stocks.StockPriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceHistoryRepository extends JpaRepository<StockPriceHistory, PriceHistoryKey> {
}
