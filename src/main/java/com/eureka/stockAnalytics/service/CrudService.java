package com.eureka.stockAnalytics.service;

import com.eureka.stockAnalytics.entity.crud.Person;
import com.eureka.stockAnalytics.repository.crud.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrudService {
    @Autowired
    private PersonRepo personRepo;

    public CrudService() {
    }

    public CrudService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public List<Person> getAllpersons() {
        return personRepo.findAll();
    }
}
