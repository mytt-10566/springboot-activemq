package com.momo.springbootactivemq.queueandtopic.topic;

import com.momo.springbootactivemq.queueandtopic.config.JmsMessageSender;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
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
public class Publisher {
    
    @Value("${mq.topic.test}")
    private String testTopic;
    
    @Autowired
    private JmsMessageSender jmsMessageSender;
    
    private Destination testTopicDestination;
    
    @PostConstruct
    public void init() {
        testTopicDestination = new ActiveMQTopic(testTopic);
    }
    
    public void sendTextMessage(String message) {
        jmsMessageSender.sendTextMessage(testTopicDestination, message);
    }
}
