package com.eureka.stockAnalytics.remoteService;

import com.eureka.stockAnalytics.VO.CummReturnRequestVO;
import com.eureka.stockAnalytics.VO.CumReturnResponseVO;
import com.eureka.stockAnalytics.config.StocksClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "stocksCalculationClient",configuration = {StocksClientConfig.class},url = "${client.stock-calculations.url}")
public interface StocksCalculationClient {
    @PostMapping(value = "/calculate/cumulative-return/{fromDate}/{toDate}")
    public List<CumReturnResponseVO> getCummulativeReturns(@PathVariable(value = "fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
                                                           @PathVariable(value = "toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,
                                                           @RequestBody CummReturnRequestVO request);

    @PostMapping(value = "/calculate/daily-return/{ticker}/{fromDate}/{toDate}")
    public List<CumReturnResponseVO> getDailyReturns(@PathVariable String ticker,
                                                     @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
                                                     @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate);

}
