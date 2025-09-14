package com.eureka.stockAnalytics.repository.stocks;

import com.eureka.stockAnalytics.entity.stocks.PriceHistoryKey;
import com.eureka.stockAnalytics.entity.stocks.StockPriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceHistoryRepository extends JpaRepository<StockPriceHistory, PriceHistoryKey> {
    @Query(nativeQuery = true, value = """
            select 
                *
                from endeavour.stocks_price_history sph
            where
                sph.ticker_symbol =:ticker
            and 
                extract(year from sph.trading_date)=:year
            """)
    public List<StockPriceHistory> getSingleStockSPHforGivenYear(@Param(value = "ticker") String ticker,
                                                                 @Param(value = "year") Integer year);
}
