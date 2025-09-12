package com.eureka.stockAnalytics.VO;

public class CustomSectorVO {
    private String sectorName;
    private CustomStockVO stockList;

    public CustomSectorVO() {
    }

    public CustomSectorVO(String sectorName, CustomStockVO stockList) {
        this.sectorName = sectorName;
        this.stockList = stockList;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public CustomStockVO getStockList() {
        return stockList;
    }

    public void setStockList(CustomStockVO stockList) {
        this.stockList = stockList;
    }
}
