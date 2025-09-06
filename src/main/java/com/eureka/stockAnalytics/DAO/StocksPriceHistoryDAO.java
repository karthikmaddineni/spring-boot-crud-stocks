package com.eureka.stockAnalytics.DAO;

import com.eureka.stockAnalytics.VO.PriceHistoryVO;
import com.eureka.stockAnalytics.mappers.StockPriceHistoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class StocksPriceHistoryDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public List<PriceHistoryVO> getSpecificStockPriceHistory(String tickerSymbol, LocalDate fromDate, LocalDate toDate) {
        String sqlQurey = """
                select
                    *
                    from 
                        endeavour.stocks_price_history sph
                    where
                        sph.ticker_symbol = ?
                        and 
                        sph.trading_date between ? and ?
                """;
        Object[] inputParams = new Object[]{tickerSymbol,fromDate,toDate};
        List<PriceHistoryVO> priceHistoryList = jdbcTemplate.query(sqlQurey, inputParams, new StockPriceHistoryRowMapper());
        return priceHistoryList;
    }

    public List<PriceHistoryVO> getMultipleStockPriceHistory(LocalDate fromDate, LocalDate toDate, List<String> tickers) {
        String sqlQurey= """
                select
                    *
                    from 
                        endeavour.stocks_price_history sph
                    where
                        sph.ticker_symbol in (:tickers)
                        and 
                        sph.trading_date between :fromDate and :toDate
                """;
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("tickers",tickers);
        mapSqlParameterSource.addValue("fromDate",fromDate);
        mapSqlParameterSource.addValue("toDate",toDate);

        List<PriceHistoryVO> priceHistoryList = namedParameterJdbcTemplate.query(sqlQurey, mapSqlParameterSource, (rs, rownum) -> {
            PriceHistoryVO priceHistoryVO = new PriceHistoryVO();
            priceHistoryVO.setTickerSymbol(rs.getString("ticker_symbol"));
            priceHistoryVO.setTradingDate(rs.getDate("trading_date").toLocalDate());
            priceHistoryVO.setClosePrice(rs.getBigDecimal("close_price"));
            priceHistoryVO.setOpenPrice(rs.getBigDecimal("open_price"));
            priceHistoryVO.setHighPrice(rs.getBigDecimal("high_price"));
            priceHistoryVO.setLowPrice(rs.getBigDecimal("low_price"));
            priceHistoryVO.setVolume(rs.getBigDecimal("volume"));

            return priceHistoryVO;
        });
        return priceHistoryList;
    }
}
