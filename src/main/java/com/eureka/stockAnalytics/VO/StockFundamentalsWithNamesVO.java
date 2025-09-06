package com.eureka.stockAnalytics.VO;

import java.math.BigDecimal;

public class StockFundamentalsWithNamesVO {
    private String tickerSymbol;
    private String tickerName;
    private String sectorName;
    private String subSectorName;
    private BigDecimal marketCap;
    private BigDecimal currentRatio;

    public StockFundamentalsWithNamesVO(String tickerSymbol, String tickerName, String sectorName, String subSectorName, BigDecimal marketCap, BigDecimal currentRatio) {
        this.tickerSymbol = tickerSymbol;
        this.tickerName = tickerName;
        this.sectorName = sectorName;
        this.subSectorName = subSectorName;
        this.marketCap = marketCap;
        this.currentRatio = currentRatio;
    }

    public StockFundamentalsWithNamesVO() {

    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public String getTickerName() {
        return tickerName;
    }

    public void setTickerName(String tickerName) {
        this.tickerName = tickerName;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getSubSectorName() {
        return subSectorName;
    }

    public void setSubSectorName(String subSectorName) {
        this.subSectorName = subSectorName;
    }

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }

    public BigDecimal getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(BigDecimal currentRatio) {
        this.currentRatio = currentRatio;
    }

    @Override
    public String toString() {
        return "StockFundamentalsWithNamesVO{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
                ", tickerName='" + tickerName + '\'' +
                ", sectorName='" + sectorName + '\'' +
                ", subSectorName='" + subSectorName + '\'' +
                ", marketCap=" + marketCap +
                ", currentRatio=" + currentRatio +
                '}'+'\n';
    }
}
