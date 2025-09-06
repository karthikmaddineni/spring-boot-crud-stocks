package com.eureka.stockAnalytics.controllers;

import com.eureka.stockAnalytics.VO.PriceHistoryVO;
import com.eureka.stockAnalytics.service.FinanceAnalylticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/financeAnalytics")
public class FinanceController {

    @Autowired
    FinanceAnalylticService financeAnalylticService;

    @GetMapping(value = "/getSpecificStockPriceHistory/{inputTicker}")
    public List<PriceHistoryVO> getSpecificStockPriceHistory(@PathVariable(value = "inputTicker") String tickerSymbol,
                                                             @RequestParam(value = "fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
                                                             @RequestParam(value = "toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate){
        return financeAnalylticService.getSpecificStockPriceHistory(tickerSymbol,fromDate,toDate);
    }

    @GetMapping(value = "/getMultipleStocksPriceHistory")
    public List<PriceHistoryVO> getMultipleStocksPriceHistory(@RequestParam(value = "fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
                                                              @RequestParam(value = "toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,
                                                              @RequestBody List<String> tickers){
        return financeAnalylticService.getMultipleStockPriceHistory(fromDate,toDate,tickers);
    }
}
