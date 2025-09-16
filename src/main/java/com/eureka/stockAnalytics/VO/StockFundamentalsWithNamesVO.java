package com.eureka.stockAnalytics.VO;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
@Schema(name = "Stock Fundamentals Data", description = "it hold details about the each stock its sector,subsector")
public class StockFundamentalsWithNamesVO {
    @Schema(name = "Ticker Symbol",description = "give stocks ticker-symbol", example = "EIT")
    private String tickerSymbol;
    @Schema(name = "ticker name",description = "give stocks tickerNAme",example = "Eureka Info Tech")
    private String tickerName;
    @Schema(name = "sector name",description = "give stocks sectorname",example = "IT")
    private String sectorName;
    @Schema(name = "subsector name",description = "give stocks subsectorname",example = "information systems")
    private String subSectorName;
    @Schema(name = "market cap",description = "give stocks marketcap", example = "40000000")
    private BigDecimal marketCap;
    @Schema(name = "current ratio",description = "give stocks current ratio", example = "3.45")
    private BigDecimal currentRatio;
    @Schema(name = "cumulative return",description = "give stocks cumulative return", example = "2.3")
    private BigDecimal cumulativeReturn;

    public StockFundamentalsWithNamesVO(String tickerSymbol, String tickerName, BigDecimal marketCap, BigDecimal cumulativeReturn) {
        this.tickerSymbol = tickerSymbol;
        this.tickerName = tickerName;
        this.marketCap = marketCap;
        this.cumulativeReturn = cumulativeReturn;
    }

    public BigDecimal getCumulativeReturn() {
        return cumulativeReturn;
    }

    public void setCumulativeReturn(BigDecimal cumulativeReturn) {
        this.cumulativeReturn = cumulativeReturn;
    }

    public StockFundamentalsWithNamesVO(String tickerSymbol, String tickerName, String sectorName, String subSectorName, BigDecimal marketCap, BigDecimal currentRatio, BigDecimal cumulativeReturn) {
        this.tickerSymbol = tickerSymbol;
        this.tickerName = tickerName;
        this.sectorName = sectorName;
        this.subSectorName = subSectorName;
        this.marketCap = marketCap;
        this.currentRatio = currentRatio;
        this.cumulativeReturn = cumulativeReturn;
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
