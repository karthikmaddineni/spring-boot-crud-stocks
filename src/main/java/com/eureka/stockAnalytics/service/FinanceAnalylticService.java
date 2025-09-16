package com.eureka.stockAnalytics.service;

import com.eureka.stockAnalytics.DAO.SectorLookupDAO;
import com.eureka.stockAnalytics.DAO.StockFundamentalsDAO;
import com.eureka.stockAnalytics.DAO.StocksPriceHistoryDAO;
import com.eureka.stockAnalytics.DAO.SubSectorDAO;
import com.eureka.stockAnalytics.VO.*;
import com.eureka.stockAnalytics.entity.stocks.*;
import com.eureka.stockAnalytics.exception.StockException;
import com.eureka.stockAnalytics.remoteService.StocksCalculationClient;
import com.eureka.stockAnalytics.repository.stocks.PriceHistoryRepository;
import com.eureka.stockAnalytics.repository.stocks.SectorLookupRepository;
import com.eureka.stockAnalytics.repository.stocks.StockFundamentalsRepository;
import com.eureka.stockAnalytics.repository.stocks.SubSectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
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
    private PriceHistoryRepository priceHistoryRepository;
    private StocksCalculationClient stocksCalculationClient;
    //private CummReturnRequestVO cummReturnRequestVO;

    @Autowired
    public FinanceAnalylticService(StocksPriceHistoryDAO stocksPriceHistoryDAO,
                                   SectorLookupDAO sectorLookupDAO,
                                   SubSectorDAO subSectorDAO,
                                   StockFundamentalsDAO stockFundamentalsDAO,
                                   StockFundamentalsRepository stockFundamentalsRepository,
                                   SectorLookupRepository sectorLookupRepository,
                                   SubSectorRepository subSectorRepository,
                                   PriceHistoryRepository priceHistoryRepository,
                                   StocksCalculationClient stocksCalculationClient
                                  ){
        this.stockFundamentalsDAO = stockFundamentalsDAO;
        this.stocksPriceHistoryDAO = stocksPriceHistoryDAO;
        this.sectorLookupDAO = sectorLookupDAO;
        this.subSectorDAO = subSectorDAO;
        this.stockFundamentalsRepository = stockFundamentalsRepository;
        this.sectorLookupRepository = sectorLookupRepository;
        this.subSectorRepository = subSectorRepository;
        this.priceHistoryRepository = priceHistoryRepository;
        this.stocksCalculationClient = stocksCalculationClient;
        //this.cummReturnRequestVO = cummReturnRequestVO;
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

    public Optional<Sector> getSectorLookupById(Integer sectorId) {
        return sectorLookupRepository.findById(sectorId);
    }


    public List<StockFundamentals> getAllStocksBySectorID(Integer sectorId) {
//        Integer sectorIdInt = Integer.valueOf(sectorId);
     return stockFundamentalsRepository.findAll()
                .stream()
                .filter(s-> sectorId.equals(s.getSector().getSectorId())).toList();
    }

    public StockPriceHistory getStockPriceHistoryForDay(String ticker,LocalDate tradingDate) {

        PriceHistoryKey key = new PriceHistoryKey();
        key.setTickerSymbol(ticker);
        key.setTradingDate(tradingDate);
        Optional<StockPriceHistory> output = priceHistoryRepository.findById(key);
        if(output.isPresent()){
            return output.get();
        }else {
            throw new IllegalArgumentException("That date was any holiday");
        }
    }

    public List<TopStockBySectorVO> getTopStockForEachSector() {
        return stockFundamentalsRepository.getTopStockBySector();
    }
    public List<TopStockBySectorVO> getTopNStocks(Integer limitvalue){
        return stockFundamentalsRepository.getTopNStocks(limitvalue);
    }

    public Map<Month,List<StockPriceHistory>> getSingleStockSPHforGivenYear(String ticker,Integer year) {
        List<StockPriceHistory> stockPriceHistoryList = priceHistoryRepository.getSingleStockSPHforGivenYear(ticker, year);

        Map<Month,List<StockPriceHistory>> listByMonth =stockPriceHistoryList.stream()
                .collect(Collectors.groupingBy(stockPriceHistory -> {
                   return stockPriceHistory.getTradingDate().getMonth();
                }));
        return listByMonth;
    }

    public List<StockFundamentals> getNonNullCurrentRatioStocks() {
        return stockFundamentalsRepository.getNonNullCurrentRatioStocks();
    }

    public List<StockFundamentals> getTopNNStocks(Integer number) {
        return stockFundamentalsDAO.getTopNNStocks(number);
    }

    public List<StockFundamentals> getTopNNNStocks(Integer number) {
        return stockFundamentalsDAO.getTopNNNNStocks(number);
    }

    public List<StockFundamentalsWithNamesVO> getTopNPerformingStocks(Integer num, LocalDate fromDate, LocalDate toDate) {
        List<StockFundamentalsWithNamesVO> allStocksFundamentalsList = stockFundamentalsDAO.getAllStocksFundamentalsWithNames();

        Map<String, StockFundamentalsWithNamesVO> stockFundamentalsMap = allStocksFundamentalsList.stream()
                .collect(Collectors.toMap(StockFundamentalsWithNamesVO::getTickerSymbol,
                stockFundamentalsWithNamesVO -> stockFundamentalsWithNamesVO));

        List<String> allTickersList = allStocksFundamentalsList.stream()
                .map(StockFundamentalsWithNamesVO::getTickerSymbol)
                .collect(Collectors.toList());

        CummReturnRequestVO cummReturnRequestVO = new CummReturnRequestVO(allTickersList);

        List<CumReturnResponseVO> cummulativeReturns = stocksCalculationClient.getCummulativeReturns(fromDate,toDate,cummReturnRequestVO);
////map of ticker and its cummulativereturn
//        Map<String, BigDecimal> cummulateReturnMap = cummulativeReturns.stream().collect(Collectors.toMap(CumReturnResponseVO::getTickers, CumReturnResponseVO::getCumulativeReturn));
////list of allstockfund with cummulateReturn
//        allStocksFundamentalsList.forEach(stock->{
//            stock.setCumulativeReturn(cummulateReturnMap.get(stock.getTickerSymbol()));
//        });
//        //
//        Map<String, List<StockFundamentalsWithNamesVO>> groupedByMapwithCRandSubName = allStocksFundamentalsList.stream().collect(Collectors.groupingBy(StockFundamentalsWithNamesVO::getSubSectorName));
//
//        groupedByMapwithCRandSubName.forEach((a,b)->{
//            b.stream().collect(Collectors.groupingBy(StockFundamentalsWithNamesVO::getCumulativeReturn));
//        });

//        if(cummulativeReturns.isEmpty()){
//            throw new StockException("The internal server error");
//        }
        List<CumReturnResponseVO> intermediate = cummulativeReturns.stream()
                .filter(cummReturnResponseVO -> cummReturnResponseVO.getCumulativeReturn()!=null)
                .sorted(Comparator.comparing(CumReturnResponseVO::getCumulativeReturn).reversed())
                .limit(num)
                .collect(Collectors.toList());

        List<StockFundamentalsWithNamesVO> finalOutputList = new ArrayList<>();
        intermediate.forEach(input -> {
            StockFundamentalsWithNamesVO stockFundamentalsWithNamesVO = stockFundamentalsMap.get(input.getTickers());
            stockFundamentalsWithNamesVO.setCumulativeReturn(input.getCumulativeReturn());
            finalOutputList.add(stockFundamentalsWithNamesVO);
        });
        return finalOutputList;
    }

    public List<CumReturnResponseVO> getTopNNPerformingStocks(String ticker, LocalDate fromDate, LocalDate toDate) {
        return stocksCalculationClient.getDailyReturns(ticker,fromDate,toDate);
    }

//    public List<StockFundamentals> getTopNPerformingFromEachSubSector() {
//        return
//    }

//    public List<TopStockBySectorVO> getTop5StockForEachSector() {
//        List<TopStockBySectorVO> top5StockBySectorVOS = stockFundamentalsRepository.getTop5StockBySector();
//
//        Map<String,List<TopStockBySectorVO>> median = top5StockBySectorVOS.stream()
//                .collect(Collectors.groupingBy(TopStockBySectorVO::getSectorName));
//
//        List<CustomStockVO> finalOutput = new ArrayList<>();
//
//        median.forEach((sectorName,topStockList)->{
//            topStockList.stream().map(topStockBySectorVO -> {
////                CustomStockVO customStockVO = new CustomStockVO(
////                        topStockBySectorVO.getTickerName();
////                        topStockBySectorVO.getMarketCap());
////                return customStockVO;
////            }).collect(Collectors.toList());
//
//        });
//        return stockFundamentalsRepository.getTop5StockBySector();
//    }
}
