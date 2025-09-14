package com.eureka.stockAnalytics.controllers;

import com.eureka.stockAnalytics.VO.*;
import com.eureka.stockAnalytics.entity.stocks.Sector;
import com.eureka.stockAnalytics.entity.stocks.StockFundamentals;
import com.eureka.stockAnalytics.entity.stocks.StockPriceHistory;
import com.eureka.stockAnalytics.entity.stocks.SubSector;
import com.eureka.stockAnalytics.service.FinanceAnalylticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
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
        //    Assignment:
        //            "Write a GET API to get Sector from database using JdbcTemple
        //            /sectors
        //sectors/{sector-id}
        //"
    @GetMapping(value = "/getAllSectorIDandNames")
    public List<SectorVO> getAllSectorIDandNames(){
        System.out.println("Hitted Controller");
        return financeAnalylticService.getAllSectorIDandNames();
    }
    @GetMapping(value = "/getSectorNameWithSectorId")
    public List<SectorVO> getSectorNameWithSectorId(@RequestParam(value = "sectorId") BigDecimal sectorId){
        System.out.println("Hitted Controller");
        return financeAnalylticService.getSectorNameWithSectorId(sectorId);
    }
    @GetMapping(value = "/getAllSubSectors")
    public Map<BigDecimal,List<SubSectorVO>> getAllSubSectors(){
        return financeAnalylticService.getAllSubSectors();
    }
    @GetMapping(value = "/getAllStockFundamentalsJPA")
    public List<StockFundamentals> getAllStockFundamentalsJPA(){
        return financeAnalylticService.getAllStockFundamentalsJPA();
    }
    @GetMapping(value = "/getAllStockFundamentalsByIdJPA")
    public Optional<StockFundamentals> getAllStockFundamentalsByIdJPA(@RequestParam(value = "tickerName") String ticker){
        return financeAnalylticService.getAllStockFundamentalsByIdJPA(ticker);
    }
    @GetMapping(value = "/getAllSectorLookupByIdJPA")
    public List<Sector> getAllSectorLookupByIdJPA(){
        return financeAnalylticService.getAllSectorLookupByIdJPA();
    }

    @GetMapping(value = "/getAllSectors")
    public List<Sector> getAllSectors(){
        return financeAnalylticService.getAllSectors();
    }
    @GetMapping(value = "/getAllSubSubSectors")
    public List<SubSector> getAllSubSubSectors(){
        return financeAnalylticService.getAllSubSubSectors();
    }

    @GetMapping(value = "/getAllSfJPA")
    public List<StockFundamentals> getAllSfJPA(){
        return financeAnalylticService.getAllSfJPA();
    }
//assignment 1
    @GetMapping(value = "/getSectorLookupById")
    public Optional<Sector> getSectorLookupById(@RequestParam(value = "sectorId") Integer sectorId){
        return financeAnalylticService.getSectorLookupById(sectorId);
    }
    //assignment 2
    @GetMapping(value = "/getAllStocksBySectorID/{sectorID}")
    public List<StockFundamentals> getAllStocksBySectorID(@PathVariable(value = "sectorID") Integer sectorId){
        return financeAnalylticService.getAllStocksBySectorID(sectorId);
    }
    @GetMapping(value = "/getStockPriceHistoryForDay")
    public StockPriceHistory getStockPriceHistoryForDay(@RequestParam(value = "ticker") String ticker,
                                                        @RequestParam(value = "date")
                                                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate tradingDate){
        return financeAnalylticService.getStockPriceHistoryForDay(ticker,tradingDate);
    }
    @GetMapping(value = "/getTopStockForEachSector")
    public List<TopStockBySectorVO> getTopStockForEachSector(){
        return financeAnalylticService.getTopStockForEachSector();
    }
//
//    @GetMapping(value = "/getTop5StockForEachSector")
//    public List<TopStockBySectorVO> getTop5StockForEachSector(){
//        return financeAnalylticService.getTop5StockForEachSector();
//    }
    @GetMapping(value = "/getTopNStocks")
    public List<TopStockBySectorVO> getTopNStocks(@RequestParam(value = "limitvalue") Integer limitvalue){
        return financeAnalylticService.getTopNStocks(limitvalue);
    }
    //get a single stock-price-history for an given year
    @GetMapping(value = "/getSingleStockSPHforGivenYear")
    public Map<Month,List<StockPriceHistory>> getSingleStockSPHforGivenYear(@RequestParam(value = "ticker") String ticker,
                                                                            @RequestParam(value = "year") Integer year){
        return financeAnalylticService.getSingleStockSPHforGivenYear(ticker,year);
    }
    //getNonNullCurrentRatioStocks
    @GetMapping(value = "/getNonNullCurrentRatioStocks")
    public List<StockFundamentals> getNonNullCurrentRatioStocks(){
        return financeAnalylticService.getNonNullCurrentRatioStocks();
    }

    @GetMapping(value = "/getTopNNStocks")
    public List<StockFundamentals> getTopNNStocks(@RequestParam(value = "number") Integer number){
        return financeAnalylticService.getTopNNStocks(number);
    }
    @GetMapping(value = "/getTopNNNNStocks/{num}")
    public List<StockFundamentals> getTopNNNNStocks(@PathVariable Integer number){
        return financeAnalylticService.getTopNNNStocks(number);
    }
    @PostMapping(value = "/getTopNPerformingStocks/{num}")
    public List<StockFundamentalsWithNamesVO> getTopNPerformingStocks(@PathVariable Integer num,
                                                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
                                                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate){
        return financeAnalylticService.getTopNPerformingStocks(num,fromDate,toDate);
    }

    @PostMapping(value = "/getTopNNPerformingStocks/{ticker}/{fromDate}/{toDate}")
    public List<CumReturnResponseVO> getTopNNPerformingStocks(@PathVariable String ticker,
                                                                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
                                                                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate){
        return financeAnalylticService.getTopNNPerformingStocks(ticker,fromDate,toDate);
    }
}
