package com.eureka.stockAnalytics.VO;

import java.io.Serializable;
import java.util.List;

public class TickersList implements Serializable {
    private List<String> tickers;

    public List<String> getTickers() {
        return tickers;
    }

    public void setTickers(List<String> tickers) {
        this.tickers = tickers;
    }

    public TickersList(List<String> tickers) {
        this.tickers = tickers;
    }
}
