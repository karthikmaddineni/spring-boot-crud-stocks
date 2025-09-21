package com.eureka.stockAnalytics.jms.queue;

import com.eureka.stockAnalytics.VO.TickersList;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class TopicProducer {
    private JmsTemplate jmsTemplate;

    public TopicProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public String sendTickersList(TickersList tickersList){
        jmsTemplate.convertAndSend("com.eureka.sampleTickersList",tickersList);
        return null;
    }
}
