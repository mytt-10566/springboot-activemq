package com.momo.springbootactivemq.queueandtopic.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jmx.JmxException;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

@Component
public class JmsMessageSender {
    private static final Logger LOG = LoggerFactory.getLogger(JmsMessageSender.class);
    
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    @Qualifier("transactedJmsTemplate")
    private JmsTemplate transactedJmsTemplate;

    public void sendTextMessage(Destination destination, String data) throws JmxException {
        System.out.println("DeliverMode：" + jmsTemplate.getDeliveryMode());
        System.out.println("SessionAcknowledgeMode：" + jmsTemplate.getSessionAcknowledgeMode());
        System.out.println("isPubSubDomain：" + jmsTemplate.isPubSubDomain());
        System.out.println("isSessionTransacted：" + jmsTemplate.isSessionTransacted());
        jmsTemplate.convertAndSend(destination, data);
    }
    
    public void sendTextMessageTransacted(Destination destination, String data) throws JmxException {
        System.out.println("DeliverMode：" + transactedJmsTemplate.getDeliveryMode());
        System.out.println("SessionAcknowledgeMode：" + transactedJmsTemplate.getSessionAcknowledgeMode());
        System.out.println("isPubSubDomain：" + transactedJmsTemplate.isPubSubDomain());
        System.out.println("isSessionTransacted：" + transactedJmsTemplate.isSessionTransacted());
        transactedJmsTemplate.convertAndSend(destination, data);
    }

    public void sendJsonMessage(Destination destination, Object data) throws JmxException {
        jmsTemplate.convertAndSend(destination, JSON.toJSONString(data));
    }
}
