package com.eureka.stockAnalytics.service;

import com.eureka.stockAnalytics.DAO.StockFundamentalsDAO;
import com.eureka.stockAnalytics.VO.CumReturnResponseVO;
import com.eureka.stockAnalytics.VO.CummReturnRequestVO;
import com.eureka.stockAnalytics.VO.StockFundamentalsWithNamesVO;
import com.eureka.stockAnalytics.exception.StockException;
import com.eureka.stockAnalytics.remoteService.StocksCalculationClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@SpringBootTest
public class FinanceAnalylticServiceTest {

    @Autowired
    private FinanceAnalylticService financeAnalylticService;
    @MockitoBean
    StockFundamentalsDAO stockFundamentalsDAO;
    @MockitoBean
    StocksCalculationClient stocksCalculationClient;
    //this test will check for the size of finalOutPut
    @Test
    public void getTop10PerformingStocksByCRTest(){
        List<StockFundamentalsWithNamesVO> dummyList =  new ArrayList<>(
                Arrays.asList(
                        new StockFundamentalsWithNamesVO("AAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("BAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("CAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("DAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("EAPL","Apple INC.", new BigDecimal(0),null)
                )
        );//mocking sfDAO
        Mockito.when(stockFundamentalsDAO.getAllStocksFundamentalsWithNames()).thenReturn(dummyList);
        List<CumReturnResponseVO> dummyCum = new ArrayList<>(
                Arrays.asList(
                        new CumReturnResponseVO("AAPL",new BigDecimal(2.3)),
                        new CumReturnResponseVO("BAPL",new BigDecimal(2.4)),
                        new CumReturnResponseVO("CAPL",new BigDecimal(2.5)),
                        new CumReturnResponseVO("DAPL",new BigDecimal(2.6)),
                        new CumReturnResponseVO("EAPL",new BigDecimal(2.7))
                )
        );//mocking scc
        Mockito.when(stocksCalculationClient.getCummulativeReturns(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(dummyCum);

        Map<Integer, Stream<CumReturnResponseVO>> top10PerformingStocksByCR = financeAnalylticService.getTop10PerformingStocksByCR(2020, 2024);
        Assertions.assertEquals(5,top10PerformingStocksByCR.size());
    }
    //this test will check for the size of value in finalOutPut
    @Test
    public void getTop10PerformingStocksByCRTest_ValueSize(){
        List<StockFundamentalsWithNamesVO> dummyList =  new ArrayList<>(
                Arrays.asList(
                        new StockFundamentalsWithNamesVO("AAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("BAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("CAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("DAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("EAPL","Apple INC.", new BigDecimal(0),null)
                )
        );//mocking sfDAO
        Mockito.when(stockFundamentalsDAO.getAllStocksFundamentalsWithNames()).thenReturn(dummyList);
        List<CumReturnResponseVO> dummyCum = new ArrayList<>(
                Arrays.asList(
                        new CumReturnResponseVO("AAPL",new BigDecimal(2.3)),
                        new CumReturnResponseVO("BAPL",new BigDecimal(2.4)),
                        new CumReturnResponseVO("CAPL",new BigDecimal(2.5)),
                        new CumReturnResponseVO("DAPL",new BigDecimal(2.6)),
                        new CumReturnResponseVO("EAPL",new BigDecimal(2.7))
                )
        );//mocking scc
        Mockito.when(stocksCalculationClient.getCummulativeReturns(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(dummyCum);

        Map<Integer, Stream<CumReturnResponseVO>> top10PerformingStocksByCR = financeAnalylticService.getTop10PerformingStocksByCR(2020, 2024);
        Assertions.assertEquals(5,top10PerformingStocksByCR.get(2020).count());
    }
    //this test will check for the dates exception
    @Test
    public void getTop10PerformingStocksByCRTestForDateException(){
        List<StockFundamentalsWithNamesVO> dummyList =  new ArrayList<>(
                Arrays.asList(
                        new StockFundamentalsWithNamesVO("AAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("BAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("CAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("DAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("EAPL","Apple INC.", new BigDecimal(0),null)
                )
        );//mocking sfDAO
        Mockito.when(stockFundamentalsDAO.getAllStocksFundamentalsWithNames()).thenReturn(dummyList);
        List<CumReturnResponseVO> dummyCum = new ArrayList<>(
                Arrays.asList(
                        new CumReturnResponseVO("AAPL",new BigDecimal(2.3)),
                        new CumReturnResponseVO("BAPL",new BigDecimal(2.4)),
                        new CumReturnResponseVO("CAPL",new BigDecimal(2.5)),
                        new CumReturnResponseVO("DAPL",new BigDecimal(2.6)),
                        new CumReturnResponseVO("EAPL",new BigDecimal(2.7))
                )
        );//mocking scc
        Mockito.when(stocksCalculationClient.getCummulativeReturns(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(dummyCum);

        Assertions.assertThrows(IllegalArgumentException.class,()->financeAnalylticService.getTop10PerformingStocksByCR(2024, 2020));
    }

    @Test
    public void getTopNPerformingStocksTest(){

        List<StockFundamentalsWithNamesVO> dummyList =  new ArrayList<>(
                Arrays.asList(
                        new StockFundamentalsWithNamesVO("AAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("BAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("CAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("DAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("EAPL","Apple INC.", new BigDecimal(0),null)
                )
        );//mocking sfDAO
        Mockito.when(stockFundamentalsDAO.getAllStocksFundamentalsWithNames()).thenReturn(dummyList);


        List<CumReturnResponseVO> dummyCum = new ArrayList<>(
                Arrays.asList(
                        new CumReturnResponseVO("AAPL",new BigDecimal(2.3)),
                        new CumReturnResponseVO("BAPL",new BigDecimal(2.4)),
                        new CumReturnResponseVO("CAPL",new BigDecimal(2.5)),
                        new CumReturnResponseVO("DAPL",new BigDecimal(2.6)),
                        new CumReturnResponseVO("EAPL",new BigDecimal(2.7))
                )
        );
        //mocking scc
        Mockito.when(stocksCalculationClient.getCummulativeReturns(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(dummyCum);

        //stockFundamentalsDAO.getAllStocksFundamentalsWithNames();
        List<StockFundamentalsWithNamesVO> topNPerformingStocks = financeAnalylticService.getTopNPerformingStocks(3, LocalDate.now().minusMonths(6), LocalDate.now());
        Assertions.assertEquals(3,topNPerformingStocks.size());
    }

    @Test
    public void getTopNPerformingStocksTest_HappyPath(){

        List<StockFundamentalsWithNamesVO> dummyList =  new ArrayList<>(
                Arrays.asList(
                        new StockFundamentalsWithNamesVO("AAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("BAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("CAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("DAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("EAPL","Apple INC.", new BigDecimal(0),null)
                )
        );

        List<CumReturnResponseVO> dummyCum = new ArrayList<>(
                Arrays.asList(
                        new CumReturnResponseVO("AAPL",new BigDecimal(2.3)),
                        new CumReturnResponseVO("BAPL",new BigDecimal(2.4)),
                        new CumReturnResponseVO("CAPL",new BigDecimal(2.5)),
                        new CumReturnResponseVO("DAPL",new BigDecimal(2.6)),
                        new CumReturnResponseVO("EAPL",new BigDecimal(2.7))
                )
        );
        //mocking sfDAO
        Mockito.when(stockFundamentalsDAO.getAllStocksFundamentalsWithNames()).thenReturn(dummyList);
        //mocking scc
        Mockito.when(stocksCalculationClient.getCummulativeReturns(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(dummyCum);
        stockFundamentalsDAO.getAllStocksFundamentalsWithNames();
        List<StockFundamentalsWithNamesVO> topNPerformingStocks = financeAnalylticService.getTopNPerformingStocks(3, LocalDate.now().minusMonths(6), LocalDate.now());
        //Assertions.assertEquals(1,topNPerformingStocks.size());

        Assertions.assertEquals("EAPL",topNPerformingStocks.get(0).getTickerSymbol());
    }

    @Test
    public void getTopNPerformingStocksTest_StockException(){

        List<StockFundamentalsWithNamesVO> dummyList =  new ArrayList<>(
                Arrays.asList(
                        new StockFundamentalsWithNamesVO("AAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("BAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("CAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("DAPL","Apple INC.", new BigDecimal(0),null),
                        new StockFundamentalsWithNamesVO("EAPL","Apple INC.", new BigDecimal(0),null)
                )
        );

        List<CumReturnResponseVO> dummyCum = new ArrayList<>(
                Arrays.asList(
                        new CumReturnResponseVO("AAPL",new BigDecimal(2.3)),
                        new CumReturnResponseVO("BAPL",new BigDecimal(2.4)),
                        new CumReturnResponseVO("CAPL",new BigDecimal(2.5)),
                        new CumReturnResponseVO("DAPL",new BigDecimal(2.6)),
                        new CumReturnResponseVO("EAPL",new BigDecimal(2.7))
                )
        );
        //mocking sfDAO
        Mockito.when(stockFundamentalsDAO.getAllStocksFundamentalsWithNames()).thenReturn(dummyList);
        //mocking scc
        Mockito.when(stocksCalculationClient.getCummulativeReturns(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(new ArrayList<>());

        Assertions.assertThrows(StockException.class,()->financeAnalylticService.getTopNPerformingStocks(3,LocalDate.now(),LocalDate.now().minusMonths(6)));
    }
}
