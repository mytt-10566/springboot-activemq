package com.momo.springbootactivemq.queueandtopic.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jmx.JmxException;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

@Component
public class JmsMessageSender {
    private static final Logger LOG = LoggerFactory.getLogger(JmsMessageSender.class);
    
    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    public void sendTextMessage(Destination destination, String data) throws JmxException {
        jmsTemplate.convertAndSend(destination, data);
    }

    public void sendJsonMessage(Destination destination, Object data) throws JmxException {
        jmsTemplate.convertAndSend(destination, JSON.toJSONString(data));
    }
}
