package com.eureka.stockAnalytics.DAO;

import com.eureka.stockAnalytics.VO.SectorVO;
import com.eureka.stockAnalytics.VO.SubSectorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SubSectorDAO {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<SubSectorVO> getAllSubSectors(){
        String sqlQurey= """
                select 
                    *
                    from 
                        endeavour.subsector_lookup
                """;
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        List<SubSectorVO> subsectorVOS = namedParameterJdbcTemplate.query(sqlQurey, mapSqlParameterSource, (rs, rownum) -> {
            SubSectorVO subsectorLookup = new SubSectorVO();
            subsectorLookup.setSectorId(rs.getBigDecimal("sector_id"));
            subsectorLookup.setSubSectorName(rs.getString("subsector_name"));
            subsectorLookup.setSubSectorId(rs.getBigDecimal("subsector_id"));
            return subsectorLookup;
        });
        System.out.println("Hitted LookupDAO");
        return subsectorVOS;
    }


}
