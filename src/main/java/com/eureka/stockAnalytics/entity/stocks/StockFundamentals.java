package com.eureka.stockAnalytics.entity.stocks;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "stock_fundamentals",schema = "endeavour")
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

    public StockFundamentals() {
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
