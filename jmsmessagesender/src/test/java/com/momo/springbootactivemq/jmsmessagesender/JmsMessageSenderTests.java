package com.momo.springbootactivemq.jmsmessagesender;

import com.momo.springbootactivemq.jmsmessagesender.queue.TestProducer;
import com.momo.springbootactivemq.jmsmessagesender.topic.TestPublisher;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JmsMessageSenderTests {

    @Autowired
    private TestProducer testProducer;

    @Autowired
    private TestPublisher testPublisher;

    @Test
    public void sendQueue() {
        Destination destination = new ActiveMQQueue("queue.order.main.sync111");
        
        for (int i = 0; i < 10; i++) {
            testProducer.sendMessage(destination, "Queue Message" + i);
        }
    }

    @Test
    public void sendTopic() {
        Destination destination = new ActiveMQQueue("test.topic");

        for (int i = 0; i < 10; i++) {
            testPublisher.publishMessage(destination, "Topic Message" + i);
        }
    }
}
