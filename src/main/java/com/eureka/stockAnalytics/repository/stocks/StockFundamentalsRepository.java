package com.eureka.stockAnalytics.repository.stocks;

import com.eureka.stockAnalytics.VO.TopStockBySectorVO;
import com.eureka.stockAnalytics.entity.stocks.StockFundamentals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockFundamentalsRepository extends JpaRepository<StockFundamentals,String> {
    @Query(nativeQuery = true, name = "StockFundamentals.TopStocksBySector")
    public List<TopStockBySectorVO> getTopStockBySector();

    @Query(nativeQuery = true, name = "StockFundamentals.Top5StocksBySector")
    public List<TopStockBySectorVO> getTop5StockBySector();

    @Query(nativeQuery = true, value = """
            select sf.ticker_symbol,sf.sector_id,sf.sector_name,sf.ticker_name,sf.market_cap from endeavour.stock_fundamentals sf where sf.market_cap is not null order by sf.market_cap desc limit :limitvalue
            """)
    public List<TopStockBySectorVO> getTopNStocks(@Param(value = "limitvalue") Integer limitvalue);
//here if we make it false, we can use the enities names, instead of using db's columns and names
    @Query(nativeQuery = true,value = """
            select
                *
                from
                    endeavour.stock_fundamentals sf
                where
                    sf.current_ratio is not null
                order by sf.current_ratio desc 
            """)
    public List<StockFundamentals> getNonNullCurrentRatioStocks();
}
