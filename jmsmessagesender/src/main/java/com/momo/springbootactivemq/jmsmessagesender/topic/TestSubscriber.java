package com.momo.springbootactivemq.jmsmessagesender.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class TestSubscriber {

    @JmsListener(destination = "test.topic")
    public void receiveSubscriber(String text) {
        System.out.println("Subscriber收到的报文为:" + text);
    }

    @JmsListener(destination = "test.topic")
    public void receiveSubscriber2(String text) {
        System.out.println("Subscriber2收到的报文为:" + text);
    }
}