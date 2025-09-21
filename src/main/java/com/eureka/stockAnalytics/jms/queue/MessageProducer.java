package com.eureka.stockAnalytics.jms.queue;

import com.eureka.stockAnalytics.VO.MessageVO;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
    private JmsTemplate jmsTemplate;


    public MessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void produceMessage(MessageVO messageVO){
        jmsTemplate.convertAndSend("com.eureka.simpleMessageQueue",messageVO);
    }
}
