package com.eureka.stockAnalytics.DAO;

import com.eureka.stockAnalytics.VO.PriceHistoryVO;
import com.eureka.stockAnalytics.VO.StockFundamentalsWithNamesVO;
import com.eureka.stockAnalytics.mappers.StockPriceHistoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDate;
import java.util.List;

public class StockFundamentalsDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public List<StockFundamentalsWithNamesVO> getAllStocksFundamentalsWithNames() {
        String sqlQurey = """
                select
                   sf.ticker_symbol,
                   stl.ticker_name,
                   sl.sector_name,
                   ssl.subsector_name,
                   sf.market_cap,
                   sf.current_ratio
                      from
                        endeavour.stocks_fundamentals sf,
                        endeavour.sector_lookup sl,
                        endeavour.subsector_lookup ssl,
                        endeavour.stocks_lookup stl
                       where
                        sl.sector_id = sf.sector_id and
                        sf.subsector_id = ssl.subsector_id and
                        sf.ticker_symbol = stl.ticker_symbol
                """;
        List<StockFundamentalsWithNamesVO> stokcFundamentalsList= namedParameterJdbcTemplate.query(sqlQurey,(rs,rowNum)-> {
            StockFundamentalsWithNamesVO stockFundamentalsWithNamesVO = new StockFundamentalsWithNamesVO();
            stockFundamentalsWithNamesVO.setTickerName(rs.getString("ticker_name"));
            stockFundamentalsWithNamesVO.setTickerSymbol(rs.getString("ticker_symbol"));
            stockFundamentalsWithNamesVO.setSectorName(rs.getString("sector_name"));
            stockFundamentalsWithNamesVO.setSubSectorName(rs.getString("subsector_name"));
            stockFundamentalsWithNamesVO.setMarketCap(rs.getBigDecimal("market_cap"));
            stockFundamentalsWithNamesVO.setCurrentRatio(rs.getBigDecimal("current_ratio"));
            return stockFundamentalsWithNamesVO;
        });
        return stokcFundamentalsList;
    }
}
