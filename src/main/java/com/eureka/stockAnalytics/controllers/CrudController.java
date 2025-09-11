package com.eureka.stockAnalytics.controllers;

import com.eureka.stockAnalytics.entity.crud.Address;
import com.eureka.stockAnalytics.entity.crud.Person;
import com.eureka.stockAnalytics.repository.crud.PersonRepo;
import com.eureka.stockAnalytics.service.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/crud")
public class CrudController {
    private final static Logger logger = LoggerFactory.getLogger(CrudController.class);

    @Autowired
    private CrudService crudService;

    @GetMapping(value = "/getAllpersons")
    public List<Person> getAllpersons() {
        return crudService.getAllpersons();
    }

    @GetMapping(value = "/getAllAddress")
    public List<Address> getAllAddress() {
        return crudService.getAllAddress();
    }

    @PostMapping(value = "/insertPerson")
    public Person insertNewPerson(@RequestBody Person person) {
        return crudService.insertNewPerson(person);
    }


    @PostMapping(value = "/updatePerson")
    public Person updatePerson(@RequestBody Person person) {
        if(null == person.getPersonId()){
            throw new IllegalArgumentException("DuDe WHat te heck PersonID is missing....,");
        }
        return crudService.updatePerson(person);
    }

    @DeleteMapping(value = "/deletePerson")
    public void deletePerson(@RequestBody Person person){
        crudService.deletePerson(person);
    }
}



//    @ExceptionHandler({IllegalArgumentException.class})
//    public ResponseEntity<String> exceptionHandler(Exception exception){
//        return ResponseEntity.badRequest().body(exception.getMessage());
//    }
//    @ExceptionHandler({NullPointerException.class})
//    public void exceptionHandler2(Exception exception){
//        ResponseEntity.internalServerError().body(exception.getMessage());
//    }


