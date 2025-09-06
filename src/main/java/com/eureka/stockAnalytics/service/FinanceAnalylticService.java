package com.eureka.stockAnalytics.service;

import com.eureka.stockAnalytics.DAO.StocksPriceHistoryDAO;
import com.eureka.stockAnalytics.VO.PriceHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FinanceAnalylticService {

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
}
