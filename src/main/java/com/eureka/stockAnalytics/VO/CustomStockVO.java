package com.eureka.stockAnalytics.VO;

import java.math.BigDecimal;

public class CustomStockVO {
    private String tickerName;
    private BigDecimal marketCap;

    public CustomStockVO(String tickerName, BigDecimal marketCap) {
        this.tickerName = tickerName;
        this.marketCap = marketCap;
    }

    public CustomStockVO() {
    }

    public String getTickerName() {
        return tickerName;
    }

    public void setTickerName(String tickerName) {
        this.tickerName = tickerName;
    }

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }
}
