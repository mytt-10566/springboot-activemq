package com.momo.springbootactivemq.queueandtopic.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

/**
 * @author: MQG
 * @date: 2018/11/5
 */
@Service
public class Consumer {

    @JmsListener(destination = "${mq.queue.test}", containerFactory = "queueListenerFactory")
    public void receiveQueue(TextMessage text) throws Exception {
        System.out.println(Thread.currentThread().getName() + ":Consumer收到的报文为:" + text.getText());
    }

}
