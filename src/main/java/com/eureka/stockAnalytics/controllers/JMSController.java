package com.eureka.stockAnalytics.controllers;

import com.eureka.stockAnalytics.VO.TickersList;
import com.eureka.stockAnalytics.service.JMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/jms")
public class JMSController {
    @Autowired
    JMSService jmsService;

    @GetMapping(value = "/simpleMessageService")
    public String simpleMessageService(@RequestBody String text){
        return jmsService.simpleMessageService(text);
    }

    @PostMapping(value = "/simpleTopicService")
    public String simpleListService(@RequestBody TickersList tickersList){
        return jmsService.simpleListService(tickersList);
    }

}
