package com.eureka.stockAnalytics.repository.stocks;

import com.eureka.stockAnalytics.entity.stocks.SectorLook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorLookupRepository extends JpaRepository<SectorLook,Integer> {

}
