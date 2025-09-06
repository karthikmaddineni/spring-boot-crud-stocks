package com.eureka.stockAnalytics.controllers;

import com.eureka.stockAnalytics.VO.PriceHistoryVO;
import com.eureka.stockAnalytics.VO.SPHCustomRequestVO;
import com.eureka.stockAnalytics.VO.SPHCustomResponseVO;
import com.eureka.stockAnalytics.VO.StockFundamentalsWithNamesVO;
import com.eureka.stockAnalytics.service.FinanceAnalylticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/financeAnalytics")
public class FinanceController {
    private final static Logger logger= LoggerFactory.getLogger(FinanceController.class);

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
    @GetMapping(value = "/getCustomMultipleStocksPriceHistory")
    public List<SPHCustomResponseVO> getMultipleStockPriceHistoryCustom(@RequestBody SPHCustomRequestVO sphCustomRequestVO,
                                                                        @RequestParam(value = "sortBy") Optional<String> sortVariable,
                                                                        @RequestParam(value = "direction") Optional<String> sortDirection){
        logger.info("Inside getMultipleStockPriceHistoryCustom:{}",sphCustomRequestVO);
        if(sphCustomRequestVO.getFromDate().isAfter(sphCustomRequestVO.getToDate())){
            logger.debug("Bad Request from date: {} greater than to date:{}",sphCustomRequestVO.getFromDate(),sphCustomRequestVO.getToDate());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "PLS CHECK THE DATES, FROM SHOULD BE BEFORE");

        }
        return financeAnalylticService.getMultipleStockPriceHistoryCustom(sphCustomRequestVO,sortVariable,sortDirection);
    }

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class,ResponseStatusException.class})
    public void ExceptionHandler(Exception e){
        ResponseEntity.badRequest().body(e.getMessage());
    }

    @GetMapping(value = "/getAllStockFundamentalsWithNames")
    public List<StockFundamentalsWithNamesVO> getAllStocksFundamentalsWithNames(){
        return financeAnalylticService.getAllStocksFundamentalsWithNames();
    }
}
