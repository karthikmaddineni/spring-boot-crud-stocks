package com.eureka.stockAnalytics.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CumReturnResponseVO {
    private String tickerSymbol;
    private BigDecimal cumulativeReturn;

    public CumReturnResponseVO(String tickerSymbol, BigDecimal cumulativeReturn) {
        this.tickerSymbol = tickerSymbol;
        this.cumulativeReturn = cumulativeReturn;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public BigDecimal getCumulativeReturn() {
        return cumulativeReturn;
    }

    public void setCumulativeReturn(BigDecimal cumulativeReturn) {
        this.cumulativeReturn = cumulativeReturn;
    }
}
