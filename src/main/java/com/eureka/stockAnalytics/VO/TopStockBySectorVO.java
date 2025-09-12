package com.eureka.stockAnalytics.VO;

import java.math.BigDecimal;

public class TopStockBySectorVO {
    private String tickerSymbol;
    private String tickerName;
    private Integer sectorId;
    private String sectorName;
    private BigDecimal marketCap;

    public TopStockBySectorVO() {
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

    public Integer getSectorId() {
        return sectorId;
    }

    public void setSectorId(Integer sectorId) {
        this.sectorId = sectorId;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }

    public TopStockBySectorVO(String tickerSymbol, String tickerName, Integer sectorId, String sectorName, BigDecimal marketCap) {
        this.tickerSymbol = tickerSymbol;
        this.tickerName = tickerName;
        this.sectorId = sectorId;
        this.sectorName = sectorName;
        this.marketCap = marketCap;
    }
}
