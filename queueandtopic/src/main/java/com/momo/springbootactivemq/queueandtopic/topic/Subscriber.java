package com.momo.springbootactivemq.queueandtopic.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

/**
 * @author: MQG
 * @date: 2018/11/5
 */
@Service
public class Subscriber {

    @JmsListener(destination = "${mq.topic.test}", containerFactory = "topicListenerFactory")
    public void receiveTopic1(TextMessage text) throws Exception {
        System.out.println("\t" + Thread.currentThread().getName() + ":Consumer收到的报文为:" + text.getText());
    }

    @JmsListener(destination = "${mq.topic.test}", containerFactory = "topicListenerFactory")
    public void receiveTopic2(TextMessage text) throws Exception {
        System.out.println("\t\t" + Thread.currentThread().getName() + ":Consumer收到的报文为:" + text.getText());
    }

}
