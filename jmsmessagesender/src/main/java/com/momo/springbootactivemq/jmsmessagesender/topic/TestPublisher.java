package com.momo.springbootactivemq.jmsmessagesender.topic;

import com.alibaba.fastjson.JSON;
import com.momo.springbootactivemq.jmsmessagesender.config.JmsMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
public class TestPublisher {

    @Autowired
    private JmsMessageSender jmsMessageSender;

    // 发送text消息
    public void publishMessage(Destination destination, final String message) {
        jmsMessageSender.sendTopicTextMessage(destination, message);
    }

    // 发送json消息
    public void publishJsonMessage(Destination destination, final Object message) {
        jmsMessageSender.sendTopicJsonMessage(destination, JSON.toJSONString(message));
    }
}
