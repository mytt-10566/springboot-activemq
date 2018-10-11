/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Description:OrderJobNoticeListener.java
 */
package com.momo.springbootactivemq.jmsmessagesender.queue;

import org.springframework.stereotype.Service;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * 队列消息监听器
 * 
 * @author: MQG
 * @date: 2018/10/11
 */
@Service
public class QueueListener implements MessageListener {
    
    @Override
    public void onMessage(Message message) {
        System.out.println(message);
    }
}
