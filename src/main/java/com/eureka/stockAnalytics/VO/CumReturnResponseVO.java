package com.eureka.stockAnalytics.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CumReturnResponseVO {
    private String tickers;
    private BigDecimal cumulativeReturn;
    @JsonProperty("fromDate")
    private LocalDate from;
    @JsonProperty("toDate")
    private LocalDate to;

    public CumReturnResponseVO(String aapl, BigDecimal cumulativeReturn) {
    }

    public LocalDate getFromDate() {
        return from;
    }

    public void setFromDate(LocalDate fromDate) {
        this.from = fromDate;
    }

    public LocalDate getToDate() {
        return to;
    }

    public void setToDate(LocalDate toDate) {
        this.to = toDate;
    }

    public String getTickers() {
        return tickers;
    }

    public void setTickers(String tickers) {
        this.tickers = tickers;
    }

    public BigDecimal getCumulativeReturn() {
        return cumulativeReturn;
    }

    public void setCumulativeReturn(BigDecimal cumulativeReturn) {
        this.cumulativeReturn = cumulativeReturn;
    }

    public CumReturnResponseVO(String tickers, BigDecimal cumulativeReturn, LocalDate fromDate, LocalDate toDate) {
        this.tickers = tickers;
        this.cumulativeReturn = cumulativeReturn;
        this.from = fromDate;
        this.to = toDate;
    }
}
