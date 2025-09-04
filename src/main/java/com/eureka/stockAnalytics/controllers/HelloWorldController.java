package com.eureka.stockAnalytics.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    @GetMapping(value = "/helloworld")
    public String firstWebMethod(){
        return "Hello World";
    }
    //path params
    @GetMapping(value="/concateString/{name}")
    public String secondWebMethod(@PathVariable(value = "name") String name){
        return name+name;
    }
    //qurey &request params
    @GetMapping(value = "/concateTwoString")
    public String concateTwoStrings(@RequestParam(value = "first") String input1,
                                    @RequestParam(value = "last") String input2){
        return input1 + " "+input2;
    }
}
