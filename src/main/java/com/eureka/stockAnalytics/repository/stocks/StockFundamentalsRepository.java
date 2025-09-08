package com.eureka.stockAnalytics.repository.stocks;

import com.eureka.stockAnalytics.entity.stocks.StockFundamentals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockFundamentalsRepository extends JpaRepository<StockFundamentals,String> {

}
