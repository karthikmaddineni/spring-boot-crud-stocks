package com.eureka.stockAnalytics.config;

import com.eureka.stockAnalytics.service.LoginService;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class StocksClientConfig {

    LoginService loginService;

    @Autowired
    public StocksClientConfig(LoginService loginService) {
        this.loginService = loginService;
    }
    @Bean
    public RequestInterceptor getRestTemplate(){
        return requestTemplate -> requestTemplate.header("Authorization","Bearer "+loginService.getBearerToken());
    }
}
