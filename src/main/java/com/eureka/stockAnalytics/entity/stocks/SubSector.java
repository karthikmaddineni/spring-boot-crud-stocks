package com.eureka.stockAnalytics.entity.stocks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.relational.core.sql.In;

@Entity
@Table(name="subsector_lookup", schema = "endeavour")
public class SubSector {

    @Column(name = "subsector_id")
    @Id
    private Integer subsectorId;
    @Column(name = "subsector_name")
    private String subsectorName;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    @JsonIgnore
    private Sector sector;

//    public String getSectorName(){
//        return sector.getSectorName();
//    }

    public SubSector() {
    }

    public String getSectorNName(){
        return sector.getSectorName();
    }
    public Integer getSubsectorId() {
        return subsectorId;
    }

    public void setSubsectorId(Integer subsectorId) {
        this.subsectorId = subsectorId;
    }

    public String getSubsectorName() {
        return subsectorName;
    }

    public void setSubsectorName(String subsectorName) {
        this.subsectorName = subsectorName;
    }

    public SubSector(Integer subsectorId, String subsectorName) {

        this.subsectorId = subsectorId;
        this.subsectorName = subsectorName;
    }
}
