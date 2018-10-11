/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Description:QueueActiveMQConfig.java
 */
package com.momo.springbootactivemq.jmsmessagesender.topic;

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
public class TopicActiveMQConfig {

    private String topicName = "test.topic";

    @Autowired
    private TopicListener topicListener;
    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public DefaultMessageListenerContainer queueListenerContainerFactory() {
        DefaultMessageListenerContainer factory = new DefaultMessageListenerContainer();
        factory.setConnectionFactory(connectionFactory);
        factory.setDestination(new ActiveMQQueue(topicName));
        factory.setMessageListener(topicListener);
        factory.setSubscriptionDurable(true);// 持久订阅
        return factory;
    }
}
