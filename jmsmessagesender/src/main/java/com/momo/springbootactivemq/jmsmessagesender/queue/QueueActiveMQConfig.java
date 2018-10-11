/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Description:QueueActiveMQConfig.java
 */
package com.momo.springbootactivemq.jmsmessagesender.queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;

/**
 * @author: MQG
 * @date: 2018/10/11
 */
@Configuration
public class QueueActiveMQConfig {

    private String queueName = "queue.order.main.sync111";

    @Autowired
    private QueueListener queueListener;
    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public DefaultMessageListenerContainer queueListenerContainerFactory() {
        DefaultMessageListenerContainer factory = new DefaultMessageListenerContainer();
        factory.setConnectionFactory(connectionFactory);
        factory.setDestination(new ActiveMQQueue(queueName));
        factory.setMessageListener(queueListener);
        factory.setSubscriptionDurable(true);// 持久订阅
        return factory;
    }
}
