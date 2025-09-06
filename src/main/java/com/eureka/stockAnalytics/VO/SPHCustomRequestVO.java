package com.eureka.stockAnalytics.VO;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class SPHCustomRequestVO {
    private List<String> tickers;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    public List<String> getTickers() {
        return tickers;
    }

    public void setTickers(List<String> tickers) {
        this.tickers = tickers;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public SPHCustomRequestVO(List<String> tickers, LocalDate fromDate, LocalDate toDate) {
        this.tickers = tickers;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "SPHCustomRequestVO{" +
                "tickers=" + tickers +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}'+'\n';
    }
}
