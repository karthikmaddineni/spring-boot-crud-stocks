package com.eureka.stockAnalytics.controllers;

import com.eureka.stockAnalytics.entity.crud.Person;
import com.eureka.stockAnalytics.repository.crud.PersonRepo;
import com.eureka.stockAnalytics.service.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/crud")
public class CrudController {
    private final static Logger logger= LoggerFactory.getLogger(CrudController.class);

    @Autowired
    private CrudService crudService;

    @GetMapping(value = "/getAllpersons")
    public List<Person> getAllpersons(){
        return crudService.getAllpersons();
    }
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler({NullPointerException.class})
    public void exceptionHandler2(Exception exception){
        ResponseEntity.internalServerError().body(exception.getMessage());
    }

}
