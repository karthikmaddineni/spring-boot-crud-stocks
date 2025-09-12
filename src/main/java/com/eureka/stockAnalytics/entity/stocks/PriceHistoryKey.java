package com.eureka.stockAnalytics.entity.stocks;

import java.time.LocalDate;

public class PriceHistoryKey {
    private String tickerSymbol;
    private LocalDate tradingDate;

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public LocalDate getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(LocalDate tradingDate) {
        this.tradingDate = tradingDate;
    }
}
