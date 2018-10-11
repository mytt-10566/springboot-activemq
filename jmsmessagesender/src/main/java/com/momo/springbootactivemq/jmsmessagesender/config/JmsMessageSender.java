package com.momo.springbootactivemq.jmsmessagesender.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.JmxException;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * 发送消息到mq
 */
@Component
public class JmsMessageSender {
    private static final Logger LOG = LoggerFactory.getLogger(JmsMessageSender.class);
    
    @Autowired
    private JmsTemplateWrapper jmsTemplateWrapper;

    public void sendQueueTextMessage(Destination destination, final String data) throws JmxException {
        jmsTemplateWrapper.getQueueJmsTemplate().send(destination, session -> session.createTextMessage(data));
    }

    public void sendTopicTextMessage(Destination destination, final String data) throws JmxException {
        jmsTemplateWrapper.getTopicJmsTemplate().send(destination, session -> session.createTextMessage(data));
    }

    public void sendQueueJsonMessage(Destination destination, final Object data) throws JmxException {
        jmsTemplateWrapper.getQueueJmsTemplate().send(destination, session ->  session.createTextMessage(JSON.toJSONString(data)));
    }

    public void sendTopicJsonMessage(Destination destination, final Object data) throws JmxException {
        jmsTemplateWrapper.getTopicJmsTemplate().send(destination, session ->  session.createTextMessage(JSON.toJSONString(data)));;
    }
}
