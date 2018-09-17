/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Description:Subscriber.java
 */
package com.momo.springbootactivemq.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * 
 * 
 * @author: MQG
 * @date: 2018/9/7
 */
@Service
public class Subscriber {
    
    @JmsListener(destination = "test.topic", containerFactory = "topicJmsListenerContainerFactory")
    public void subscribe(String text) {
        System.out.println("============ Subscriber1 收到订阅消息：" + text);
    }

    @JmsListener(destination = "test.topic", containerFactory = "topicJmsListenerContainerFactory")
    public void subscribe2(String text) {
        System.out.println("============ Subscriber2 收到订阅消息：" + text);
    }
}
