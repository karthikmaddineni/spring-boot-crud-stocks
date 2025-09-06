package com.eureka.stockAnalytics.VO;

import java.util.List;

public class SPHCustomResponseVO {
    private String ticker;
    List<PriceHistoryVO> tradingHistory;

    public SPHCustomResponseVO(String ticker, List<PriceHistoryVO> tradingHistory) {
        this.ticker = ticker;
        this.tradingHistory = tradingHistory;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public List<PriceHistoryVO> getTradingHistory() {
        return tradingHistory;
    }

    public void setTradingHistory(List<PriceHistoryVO> tradingHistory) {
        this.tradingHistory = tradingHistory;
    }

    @Override
    public String toString() {
        return "CustomResponse{" +
                "ticker='" + ticker + '\'' +
                ", tradingHistory=" + tradingHistory +
                '}';
    }
}