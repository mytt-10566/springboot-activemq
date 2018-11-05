package com.momo.springbootactivemq.queueandtopic.queue;

import com.momo.springbootactivemq.queueandtopic.config.JmsMessageSender;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.jms.Destination;

/**
 * @author: MQG
 * @date: 2018/11/5
 */
@Service
public class Producer {
    
    @Value("${mq.queue.test}")
    private String testQueue;
    
    @Autowired
    private JmsMessageSender jmsMessageSender;
    
    private Destination testQueueDestination;
    
    @PostConstruct
    public void init() {
        testQueueDestination = new ActiveMQQueue(testQueue);
    }
    
    public void sendTextMessage(String message) {
        jmsMessageSender.sendTextMessage(testQueueDestination, message);
    }
}
