/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Description:Publisher.java
 */
package com.momo.springbootactivemq.topic.test;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @author: MQG
 * @date: 2018/9/7
 */
@Service
public class Publisher {
    
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    
    public void publish(String destinationName, String message) {
        Destination destination = new ActiveMQTopic(destinationName);
        System.out.println("====== 发布topic消息 " + message);
        jmsMessagingTemplate.convertAndSend(destination, message);
    }
}
