package com.eureka.stockAnalytics.repository.crud;

import com.eureka.stockAnalytics.entity.crud.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person,Integer> {

}
