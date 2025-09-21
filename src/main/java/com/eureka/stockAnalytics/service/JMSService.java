package com.eureka.stockAnalytics.service;

import com.eureka.stockAnalytics.VO.MessageVO;
import com.eureka.stockAnalytics.VO.TickersList;
import com.eureka.stockAnalytics.exception.StockException;
import com.eureka.stockAnalytics.jms.queue.MessageProducer;
import com.eureka.stockAnalytics.jms.queue.TopicProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JMSService {
    private MessageProducer messageProducer;
    private TopicProducer topicProducer;

    @Autowired
    public JMSService(MessageProducer messageProducer,TopicProducer topicProducer) {
        this.messageProducer = messageProducer;
        this.topicProducer = topicProducer;
    }

    public String simpleMessageService(String text) {
        MessageVO messageVO = new MessageVO();
        messageVO.setMessage(text);
        try{
            messageProducer.produceMessage(messageVO);
            return "msg sent successfully";
        } catch (Exception e) {
            throw new StockException("Something Failed While Passing Message",e);
        }
    }

    public String simpleListService(TickersList tickersList) {
        try {
        topicProducer.sendTickersList(tickersList);
        return "ticker passed";
        }catch (Exception exception){
            throw new StockException("Something Failed While Passing Tickers",exception);
        }
    }
}
