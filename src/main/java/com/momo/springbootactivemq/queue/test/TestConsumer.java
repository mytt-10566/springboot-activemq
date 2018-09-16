package com.momo.springbootactivemq.queue.test;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class TestConsumer {

    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "test.queue")
    public void receiveQueue(String text) {
        System.out.println("Consumer收到的报文为:" + text);
    }

    // 每个队列中一个消息只能消费一次
    @JmsListener(destination = "test.queue")
    public void receiveQueue2(String text) {
        System.out.println("Consumer2收到的报文为:" + text);
    }
}