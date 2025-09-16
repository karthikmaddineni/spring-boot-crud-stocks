package com.eureka.stockAnalytics.DAO;

import com.eureka.stockAnalytics.VO.PriceHistoryVO;
import com.eureka.stockAnalytics.VO.StockFundamentalsWithNamesVO;
import com.eureka.stockAnalytics.entity.stocks.StockFundamentals;
import com.eureka.stockAnalytics.mappers.StockPriceHistoryRowMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Component
public class StockFundamentalsDAO {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Qualifier(value = "entityManagerFactory")
    EntityManager entityManager;

    public List<StockFundamentals> getTopNNStocks(Integer number){
        String sqlQurey= """
                select
                    sf
                    from endeavour.stocks_fundamentals sf
                order by sf.marcketCap desc
                """;
        TypedQuery<StockFundamentals> qurey = entityManager.createQuery(sqlQurey,StockFundamentals.class);
        qurey.setMaxResults(number);
        return qurey.getResultList();
    }

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
                        endeavour.stock_fundamentals sf,
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

    public List<StockFundamentals> getTopNNNNStocks(Integer num){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StockFundamentals> query = criteriaBuilder.createQuery(StockFundamentals.class);
        Root<StockFundamentals> root = query.from(StockFundamentals.class);
        query.select(root)
                .where(criteriaBuilder.isNotNull(root.get("marketCap")))
                .orderBy(criteriaBuilder.desc(root.get("marketCap")));

        TypedQuery<StockFundamentals> query1 = entityManager.createQuery(query);
        return query1.setMaxResults(num).getResultList();
    }
}
