package com.eureka.stockAnalytics.jms.queue;

import com.eureka.stockAnalytics.VO.MessageVO;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

        @JmsListener(containerFactory = "topicListnerFactory", destination = "com.eureka.simpleMessageQueue")
        public void readMessage(MessageVO messageVO){
            System.out.println("Message Recived to Listner is "+messageVO.getMessage());
        }

}
