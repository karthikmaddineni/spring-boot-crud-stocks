package com.eureka.stockAnalytics.entity.stocks;

import com.eureka.stockAnalytics.entity.crud.Address;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sector_lookup",schema = "endeavour")
public class Sector {
    @Column(name = "sector_id")
    @Id
    private Integer sectorId;
    @Column(name = "sector_name")
    private String sectorName;

    @OneToMany(mappedBy = "sector",fetch = FetchType.EAGER)
    private List<SubSector> subSectors;

    public Sector() {
    }

    public Sector(Integer sectorId, String sectorName) {
        this.sectorId = sectorId;
        this.sectorName = sectorName;
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
