package com.eureka.stockAnalytics.DAO;

import com.eureka.stockAnalytics.VO.PriceHistoryVO;
import com.eureka.stockAnalytics.VO.SectorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class SectorLookupDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public List<SectorVO> getSectorNameWithSectorId(BigDecimal sectorId){
        System.out.println("Hiited Qurey");
        String sqlQurey= """
                select
                    *
                    from
                        endeavour.sector_lookup sl
                    where
                        sl.sector_id=:tickers
                """;
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("tickers",sectorId);
        List<SectorVO> sectorVOS = namedParameterJdbcTemplate.query(sqlQurey, mapSqlParameterSource, (rs, rownum) -> {
            SectorVO sectorLookup = new SectorVO();
            sectorLookup.setSectorId(rs.getBigDecimal("sector_id"));
            sectorLookup.setSectorName(rs.getString("sector_name"));
            return sectorLookup;
        });
        System.out.println("Hitted LookupDAO");
        return sectorVOS;
    }

    public List<SectorVO> getAllSectorIDandNames() {
        String sqlQurey= """
                select
                    *
                    from
                        endeavour.sector_lookup sl
                """;
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        List<SectorVO> sectorsList = namedParameterJdbcTemplate.query(sqlQurey, mapSqlParameterSource, (rs, rownum) -> {
            SectorVO sectorLookup = new SectorVO();
            sectorLookup.setSectorId(rs.getBigDecimal("sector_id"));
            sectorLookup.setSectorName(rs.getString("sector_name"));
            return sectorLookup;
        });
        return sectorsList;
    }
}
