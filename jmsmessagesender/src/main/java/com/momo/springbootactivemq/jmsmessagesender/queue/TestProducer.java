package com.momo.springbootactivemq.jmsmessagesender.queue;

import com.alibaba.fastjson.JSON;
import com.momo.springbootactivemq.jmsmessagesender.config.JmsMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
public class TestProducer {

    @Autowired
    private JmsMessageSender jmsMessageSender;

    // 发送text消息，destination是发送到的队列，message是待发送的消息
    public void sendMessage(Destination destination, final String message) {
        jmsMessageSender.sendQueueTextMessage(destination, message);
    }

    // 发送json消息
    public void sendJsonMessage(Destination destination, final Object message) {
        jmsMessageSender.sendQueueJsonMessage(destination, JSON.toJSONString(message));
    }
}
