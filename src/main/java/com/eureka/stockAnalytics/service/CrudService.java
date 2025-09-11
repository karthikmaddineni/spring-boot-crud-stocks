package com.eureka.stockAnalytics.service;

import com.eureka.stockAnalytics.entity.crud.Address;
import com.eureka.stockAnalytics.entity.crud.Person;
import com.eureka.stockAnalytics.repository.crud.AddressRepo;
import com.eureka.stockAnalytics.repository.crud.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrudService {
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private AddressRepo addressRepo;


    @Autowired
    public CrudService(PersonRepo personRepo,AddressRepo addressRepo) {
        this.personRepo = personRepo;
        this.addressRepo=addressRepo;
    }
    public CrudService() {
    }

    public List<Person> getAllpersons() {
        return personRepo.findAll();
    }

    public List<Address> getAllAddress() {
        return addressRepo.findAll();
    }

    public Person insertNewPerson(Person person) {
        List<Address> addressList = person.getAddressList();
        addressList.forEach(address -> {
            address.setPerson(person);
        });
        Person savedPerson = personRepo.save(person);
        return savedPerson;
    }

    public Person updatePerson(Person person) {
        return insertNewPerson(person);
    }

    public void deletePerson(Person person) {
        personRepo.deleteById(person.getPersonId());
    }
}
