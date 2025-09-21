package com.eureka.stockAnalytics.jms.queue;

import com.eureka.stockAnalytics.VO.MessageVO;
import com.eureka.stockAnalytics.VO.TickersList;
import org.springframework.jms.annotation.JmsListener;

public class TopicConsumer {

    @JmsListener(containerFactory = "topicListnerFactory", destination = "com.eureka.sampleTickersList")
    public void readMessage(TickersList tickersList){
        System.out.println("Topic : Tickers Listener 1:"+tickersList.getTickers());
    }
    @JmsListener(containerFactory = "topicListnerFactory", destination = "com.eureka.sampleTickersList")
    public void readMessage1(TickersList tickersList){
        System.out.println("Topic : Tickers Listener 2:"+tickersList.getTickers());
    }

}
