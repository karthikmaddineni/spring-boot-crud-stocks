package com.eureka.stockAnalytics.VO;

import java.util.List;

public class CummReturnRequestVO {
    private List<String> tickers;

    public CummReturnRequestVO(List<String> tickers) {
        this.tickers = tickers;
    }

    public List<String> getTickers() {
        return tickers;
    }

    public void setTickers(List<String> tickers) {
        this.tickers = tickers;
    }
}
