package com.eureka.stockAnalytics.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/helloworld")
public class HelloWorldController {
    //here we are just doing getmethod
    @GetMapping(value = "/helloworld")
    public String firstWebMethod(){
        return "Hello World";
    }
    //path params,here we are doing path_variable passing values in path itself
    @GetMapping(value="/concateString/{name}")
    public String secondWebMethod(@PathVariable(value = "name") String name){
        return name+name;
    }
    //qurey &request params,we are passing the data in the params=>key:value
    @GetMapping(value = "/concateTwoString")
    public String concateTwoStrings(@RequestParam(value = "first") String input1,
                                    @RequestParam(value = "last") String input2){
        return input1 + " "+input2;
    }
    @PostMapping(value = "/listofTickers/{inputString}")
    public List<String> listOfTickers(@PathVariable(value = "inputString") String dummyInput,
                                      @RequestParam(value = "today") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dummyDate,
                                      @RequestBody List<String> tickersList){
        return tickersList;
    }
}
