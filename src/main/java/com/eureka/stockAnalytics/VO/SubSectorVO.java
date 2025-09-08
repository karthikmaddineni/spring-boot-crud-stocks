package com.eureka.stockAnalytics.VO;

import java.math.BigDecimal;

public class SubSectorVO {
    private BigDecimal subSectorId;
    private String subSectorName;
    private BigDecimal sectorId;

    public BigDecimal getSubSectorId() {
        return subSectorId;
    }

    public void setSubSectorId(BigDecimal subSectorId) {
        this.subSectorId = subSectorId;
    }

    public String getSubSectorName() {
        return subSectorName;
    }

    public void setSubSectorName(String subSectorName) {
        this.subSectorName = subSectorName;
    }

    public BigDecimal getSectorId() {
        return sectorId;
    }

    public void setSectorId(BigDecimal sectorId) {
        this.sectorId = sectorId;
    }

    public SubSectorVO() {
    }

    public SubSectorVO(BigDecimal subSectorId, String subSectorName, BigDecimal sectorId) {
        this.subSectorId = subSectorId;
        this.subSectorName = subSectorName;
        this.sectorId = sectorId;
    }
}
