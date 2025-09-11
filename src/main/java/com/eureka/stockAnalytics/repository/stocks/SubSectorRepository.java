package com.eureka.stockAnalytics.repository.stocks;

import com.eureka.stockAnalytics.entity.stocks.SubSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubSectorRepository extends JpaRepository<SubSector,Integer> {

}
