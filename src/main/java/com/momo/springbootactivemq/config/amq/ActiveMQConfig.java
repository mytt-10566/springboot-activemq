/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Description:ActiveMQConfig.java
 */
package com.momo.springbootactivemq.config.amq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * 单独配置queue、topic，每次只能使用一种类型的JmsMessagingTemplate或JmsTemplate
 * 
 * @author: MQG
 * @date: 2018/9/7
 */
//@Configuration
public class ActiveMQConfig {

    @Bean("topicJmsListenerContainerFactory")
    JmsListenerContainerFactory<?> jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(Boolean.TRUE);
        return factory;
    }

    @Bean("queueJmsListenerContainerFactory")
    JmsListenerContainerFactory<?> queueJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(Boolean.FALSE);
        return factory;
    }
}
