package com.eureka.stockAnalytics.service;

import com.eureka.stockAnalytics.DAO.StockFundamentalsDAO;
import com.eureka.stockAnalytics.DAO.StocksPriceHistoryDAO;
import com.eureka.stockAnalytics.VO.PriceHistoryVO;
import com.eureka.stockAnalytics.VO.SPHCustomRequestVO;
import com.eureka.stockAnalytics.VO.SPHCustomResponseVO;
import com.eureka.stockAnalytics.VO.StockFundamentalsWithNamesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

//here we get the data from DAO layer and we will perform business logic
@Service
public class FinanceAnalylticService {

    private StockFundamentalsDAO stockFundamentalsDAO;
    private StocksPriceHistoryDAO stocksPriceHistoryDAO;

    @Autowired
    public FinanceAnalylticService(StocksPriceHistoryDAO stocksPriceHistoryDAO){
        this.stocksPriceHistoryDAO = stocksPriceHistoryDAO;
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
}
