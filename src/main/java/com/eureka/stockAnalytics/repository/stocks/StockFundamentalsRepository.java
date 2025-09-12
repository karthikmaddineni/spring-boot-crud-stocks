package com.eureka.stockAnalytics.repository.stocks;

import com.eureka.stockAnalytics.VO.TopStockBySectorVO;
import com.eureka.stockAnalytics.entity.stocks.StockFundamentals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockFundamentalsRepository extends JpaRepository<StockFundamentals,String> {
    @Query(nativeQuery = true, name = "StockFundamentals.TopStocksBySector")
    public List<TopStockBySectorVO> getTopStockBySector();

    @Query(nativeQuery = true, name = "StockFundamentals.Top5StocksBySector")
    public List<TopStockBySectorVO> getTop5StockBySector();
}
