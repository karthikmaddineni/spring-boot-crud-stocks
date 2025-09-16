package com.eureka.stockAnalytics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {
    private String loginEndPoint;
    private RestTemplate restTemplate;
    //value annotation is to pick properties from the application.props and inject into application

    @Autowired
    public LoginService(@Value("${client.stock-calculations.url}") String loginUrl,
                        @Value("${client.login.username}") String userName,
                        @Value("${client.login.password}") String password){
        loginEndPoint = loginUrl +"/login";
        //restTemplate client helps you to build request to hit the external webservice with designated
        //headers and request body and can capture the response
        restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(userName,password));
        this.restTemplate = restTemplate;
    }
    public String getBearerToken(){
        ResponseEntity<String> response = restTemplate.exchange(loginEndPoint, HttpMethod.POST, null, String.class);

        return response.getBody();
    }
}
