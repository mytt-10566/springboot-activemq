/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Description:MultActiveMQConfig.java
 */
package com.momo.springbootactivemq.jmsmessagesender.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

/**
 * @author: MQG
 * @date: 2018/10/11
 */
@Configuration
public class MultActiveMQConfig {

    @Bean
    public JmsTemplateWrapper getJmsTemplateWrapper(ConnectionFactory connectionFactory) {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connectionFactory);
        cachingConnectionFactory.setSessionCacheSize(1);
        JmsTemplateWrapper wrapper = new JmsTemplateWrapper();

        // queue
        JmsTemplate queueJmsTemplate = new JmsTemplate(cachingConnectionFactory);
        queueJmsTemplate.setConnectionFactory(cachingConnectionFactory);
        queueJmsTemplate.setDeliveryPersistent(true);
        queueJmsTemplate.setPubSubDomain(false);
        queueJmsTemplate.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        queueJmsTemplate.setExplicitQosEnabled(true);
        queueJmsTemplate.setTimeToLive(604800000);
        wrapper.setQueueJmsTemplate(queueJmsTemplate);

        // topic
        JmsTemplate topicJmsTemplate = new JmsTemplate(cachingConnectionFactory);
        topicJmsTemplate.setConnectionFactory(cachingConnectionFactory);
        topicJmsTemplate.setDeliveryPersistent(true);
        topicJmsTemplate.setPubSubDomain(true);
        topicJmsTemplate.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        topicJmsTemplate.setExplicitQosEnabled(true);
        topicJmsTemplate.setTimeToLive(604800000);
        wrapper.setTopicJmsTemplate(topicJmsTemplate);

        return wrapper;
    }
}
