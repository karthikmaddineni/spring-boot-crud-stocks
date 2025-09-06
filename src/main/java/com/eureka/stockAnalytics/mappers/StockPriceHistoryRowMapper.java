package com.eureka.stockAnalytics.mappers;

import com.eureka.stockAnalytics.VO.PriceHistoryVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StockPriceHistoryRowMapper implements RowMapper<PriceHistoryVO> {

    @Override
    public PriceHistoryVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        PriceHistoryVO priceHistoryVO = new PriceHistoryVO();
        priceHistoryVO.setTickerSymbol(rs.getString("ticker_symbol"));
        priceHistoryVO.setTradingDate(rs.getDate("trading_date").toLocalDate());
        priceHistoryVO.setClosePrice(rs.getBigDecimal("close_price"));
        priceHistoryVO.setOpenPrice(rs.getBigDecimal("open_price"));
        priceHistoryVO.setHighPrice(rs.getBigDecimal("high_price"));
        priceHistoryVO.setLowPrice(rs.getBigDecimal("low_price"));
        priceHistoryVO.setVolume(rs.getBigDecimal("volume"));

        return priceHistoryVO;
    }
}
