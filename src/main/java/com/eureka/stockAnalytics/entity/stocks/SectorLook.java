package com.eureka.stockAnalytics.entity.stocks;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.relational.core.sql.In;
@Entity
@Table(name = "sector_lookup",schema = "endeavour")
public class SectorLook {
    @Column(name = "sector_id")
    @Id
    private Integer sectorId;
    @Column(name = "sector_name")
    private String sectorName;

    public SectorLook() {
    }

    public SectorLook(Integer sectorId, String sectorName) {
        this.sectorId = sectorId;
        this.sectorName = sectorName;
    }

    public Integer getSectorId() {
        return sectorId;
    }

    public void setSectorId(Integer sectorId) {
        this.sectorId = sectorId;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }
}
