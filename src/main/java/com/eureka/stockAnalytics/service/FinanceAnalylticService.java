package com.eureka.stockAnalytics.service;

import com.eureka.stockAnalytics.DAO.SectorLookupDAO;
import com.eureka.stockAnalytics.DAO.StockFundamentalsDAO;
import com.eureka.stockAnalytics.DAO.StocksPriceHistoryDAO;
import com.eureka.stockAnalytics.DAO.SubSectorDAO;
import com.eureka.stockAnalytics.VO.*;
import com.eureka.stockAnalytics.entity.stocks.Sector;
import com.eureka.stockAnalytics.entity.stocks.StockFundamentals;
import com.eureka.stockAnalytics.entity.stocks.SubSector;
import com.eureka.stockAnalytics.repository.stocks.SectorLookupRepository;
import com.eureka.stockAnalytics.repository.stocks.StockFundamentalsRepository;
import com.eureka.stockAnalytics.repository.stocks.SubSectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

//here we get the data from DAO layer and we will perform business logic
@Service
public class FinanceAnalylticService {
    private SectorLookupDAO sectorLookupDAO;
    private StockFundamentalsDAO stockFundamentalsDAO;
    private StocksPriceHistoryDAO stocksPriceHistoryDAO;
    private SubSectorDAO subSectorDAO;
    private StockFundamentalsRepository stockFundamentalsRepository;
    private SectorLookupRepository sectorLookupRepository;
    private SubSectorRepository subSectorRepository;

    @Autowired
    public FinanceAnalylticService(StocksPriceHistoryDAO stocksPriceHistoryDAO,
                                   SectorLookupDAO sectorLookupDAO,
                                   SubSectorDAO subSectorDAO,
                                   StockFundamentalsRepository stockFundamentalsRepository,
                                   SectorLookupRepository sectorLookupRepository,
                                   SubSectorRepository subSectorRepository){
        this.stocksPriceHistoryDAO = stocksPriceHistoryDAO;
        this.sectorLookupDAO = sectorLookupDAO;
        this.subSectorDAO = subSectorDAO;
        this.stockFundamentalsRepository = stockFundamentalsRepository;
        this.sectorLookupRepository = sectorLookupRepository;
        this.subSectorRepository = subSectorRepository;
    }
    public List<PriceHistoryVO> getSpecificStockPriceHistory(String tickerSymbol, LocalDate fromDate, LocalDate toDate) {
        return stocksPriceHistoryDAO.getSpecificStockPriceHistory(tickerSymbol, fromDate, toDate);

    }
    public List<PriceHistoryVO> getMultipleStockPriceHistory(LocalDate fromDate, LocalDate toDate, List<String> tickers) {
        return stocksPriceHistoryDAO.getMultipleStockPriceHistory(fromDate,toDate,tickers);
    }

    public List<SPHCustomResponseVO> getMultipleStockPriceHistoryCustom(SPHCustomRequestVO sphCustomRequestVO,
                                                                        Optional<String> sortVariable,
                                                                        Optional<String> sortDirection) {
        String sort = sortVariable.orElse("trading_date");
        String direction = sortDirection.orElse("asc");
        List<PriceHistoryVO> mutlipleStocksPriceHistory = stocksPriceHistoryDAO.getMultipleStockPriceHistory(
                                                                        sphCustomRequestVO.getFromDate(),
                                                                        sphCustomRequestVO.getToDate(),sphCustomRequestVO.getTickers());
        Map<String,List<PriceHistoryVO>> priceHistoryByTickersMap = mutlipleStocksPriceHistory.stream()
                .collect(Collectors.groupingBy(PriceHistoryVO::getTickerSymbol));

        List<SPHCustomResponseVO> finalOutPutList = new ArrayList<>();
        priceHistoryByTickersMap.forEach((ticker,pricehistoryList)->{
            //sorting will be done her
            //Comparator<PriceHistoryVO> compare = null;
            Comparator<PriceHistoryVO> compare = switch (sort.toLowerCase()) {
                case "lowprice" -> Comparator.comparing(PriceHistoryVO::getLowPrice);
                case "openprice" -> Comparator.comparing(PriceHistoryVO::getOpenPrice);
                case "closeprice" -> Comparator.comparing(PriceHistoryVO::getClosePrice);
                case "highprice" -> Comparator.comparing(PriceHistoryVO::getHighPrice);
                default -> Comparator.comparing(PriceHistoryVO::getTradingDate);
            };

            if(direction.equalsIgnoreCase("DESC")) compare=compare.reversed();
            pricehistoryList.sort(compare);
            //pricehistoryList.stream().sorted(Comparator.comparing(sortVariable.));
            SPHCustomResponseVO sphCustomResponseVO1 = new SPHCustomResponseVO(ticker,pricehistoryList);
            finalOutPutList.add(sphCustomResponseVO1);
        });
        return finalOutPutList;
    }

    public List<StockFundamentalsWithNamesVO> getAllStocksFundamentalsWithNames() {
        return stockFundamentalsDAO.getAllStocksFundamentalsWithNames();
    }

    public List<SectorVO> getAllSectorIDandNames(){
        return sectorLookupDAO.getAllSectorIDandNames();
    }
    public List<SectorVO> getSectorNameWithSectorId(BigDecimal sectorId) {
        System.out.println("Hitted Service Layer");
        return sectorLookupDAO.getSectorNameWithSectorId(sectorId);
    }

    public Map<BigDecimal,List<SubSectorVO>> getAllSubSectors() {
        List<SubSectorVO> subSectorObject = subSectorDAO.getAllSubSectors();
        Map<BigDecimal,List<SubSectorVO>> newSubSectorObject = subSectorObject.stream()
                .collect(Collectors.groupingBy(SubSectorVO::getSectorId));
        return newSubSectorObject;
    }

    public List<StockFundamentals> getAllStockFundamentalsJPA() {
        return stockFundamentalsRepository.findAll();
    }
    public Optional<StockFundamentals> getAllStockFundamentalsByIdJPA(String ticker) {
        return stockFundamentalsRepository.findById(ticker);
    }

    public List<Sector> getAllSectorLookupByIdJPA() {
        return sectorLookupRepository.findAll();
    }

    public List<Sector> getAllSectors() {
        return sectorLookupRepository.findAll();
    }

    public List<SubSector> getAllSubSubSectors() {
        return subSectorRepository.findAll();
    }

    public List<StockFundamentals> getAllSfJPA() {
        return stockFundamentalsRepository.findAll();
    }
}
