package com.eureka.stockAnalytics.entity.stocks;

import com.eureka.stockAnalytics.VO.TopStockBySectorVO;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "stock_fundamentals",schema = "endeavour")
@NamedNativeQuery(name = "StockFundamentals.Top5StocksBySector", query = """
        with CTE_1 as (select
                sf.ticker_symbol,sl.sector_id ,sl.sector_name ,sl2.ticker_name,sf.market_cap,
                rank() over (partition by sf.sector_id order by sf.market_cap desc) as mkc
                from
                    endeavour.stock_fundamentals sf,
                    endeavour.sector_lookup sl,
                    endeavour.stocks_lookup sl2\s
                where\s
                	sf.ticker_symbol = sl2.ticker_symbol and sf.sector_id = sl.sector_id)
        select * from CTE_1 where mkc in (1,2,3,4,5)
        """,resultSetMapping = "StockFundamentals.TopStocksBySector")
@SqlResultSetMapping(name = "StockFundamentals.TopStocksBySector",
classes = @ConstructorResult(targetClass = TopStockBySectorVO.class,
columns = {
        @ColumnResult(name="ticker_symbol",type = String.class),
        @ColumnResult(name = "ticker_name",type = String.class),
        @ColumnResult(name = "sector_id",type = Integer.class),
        @ColumnResult(name = "sector_name",type = String.class),
        @ColumnResult(name = "market_cap",type = BigDecimal.class)
}))
@NamedNativeQuery(name = "StockFundamentals.TopStocksBySector", query = """
        with CTE_1 as (select
                sf.ticker_symbol,sl.sector_id ,sl.sector_name ,sl2.ticker_name,sf.market_cap,
                rank() over (partition by sf.sector_id order by sf.market_cap desc) as mkc
                from
                    endeavour.stock_fundamentals sf,
                    endeavour.sector_lookup sl,
                    endeavour.stocks_lookup sl2\s
                where\s
                	sf.ticker_symbol = sl2.ticker_symbol and sf.sector_id = sl.sector_id)
        select * from CTE_1 where mkc = 1
        """,resultSetMapping = "StockFundamentals.Top5StocksBySector")
@SqlResultSetMapping(name = "StockFundamentals.Top5StocksBySector",
        classes = @ConstructorResult(targetClass = TopStockBySectorVO.class,
                columns = {
                        @ColumnResult(name="ticker_symbol",type = String.class),
                        @ColumnResult(name = "ticker_name",type = String.class),
                        @ColumnResult(name = "sector_id",type = Integer.class),
                        @ColumnResult(name = "sector_name",type = String.class),
                        @ColumnResult(name = "market_cap",type = BigDecimal.class)
                }))
public class StockFundamentals {
    @Column(name = "ticker_symbol")
    @Id
    private String tickerSymbol;
//    @Column(name="sector_id")
//    private Integer sectorId;
//    @Column(name = "subsector_id")
//    private Integer subSectorId;
    @Column(name = "market_cap")
    private BigDecimal markerCap;
    @Column(name = "current_ratio")
    private BigDecimal currentRatio;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;

    @ManyToOne
    @JoinColumn(name = "subsector_id")
    private SubSector subSector;

    public Sector getSector() {
        return sector;
    }

    public SubSector getSubSector() {
        return subSector;
    }

    public StockFundamentals() {
    }

    public StockFundamentals(String tickerSymbol, BigDecimal markerCap, BigDecimal currentRatio,
                             Sector sector, SubSector subSector) {
        this.tickerSymbol = tickerSymbol;
        this.markerCap = markerCap;
        this.currentRatio = currentRatio;
        this.sector = sector;
        this.subSector = subSector;
    }

    public StockFundamentals(String tickerSymbol, Integer sectorId, Integer subSectorId, BigDecimal markerCap, BigDecimal currentRatio) {
        this.tickerSymbol = tickerSymbol;
//        this.sectorId = sectorId;
//        this.subSectorId = subSectorId;
        this.markerCap = markerCap;
        this.currentRatio = currentRatio;
    }

    @Override
    public String toString() {
        return "StockFundamentals{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
//                ", sectorId=" + sectorId +
//                ", subSectorId=" + subSectorId +
                ", markerCap=" + markerCap +
                ", currentRatio=" + currentRatio +
                '}'+'\n';
    }
    public Integer getSectorId(){
        return sector.getSectorId();
    }
    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public String getSectorName(){
        return sector.getSectorName();
    }
    public String getSubSectorName(){
        return subSector.getSubsectorName();
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

//    public Integer getSectorId() {
//        return sectorId;
//    }
//
//    public void setSectorId(Integer sectorId) {
//        this.sectorId = sectorId;
//    }
//
//    public Integer getSubSectorId() {
//        return subSectorId;
//    }
//
//    public void setSubSectorId(Integer subSectorId) {
//        this.subSectorId = subSectorId;
//    }

    public BigDecimal getMarkerCap() {
        return markerCap;
    }

    public void setMarkerCap(BigDecimal markerCap) {
        this.markerCap = markerCap;
    }

    public BigDecimal getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(BigDecimal currentRatio) {
        this.currentRatio = currentRatio;
    }
}
