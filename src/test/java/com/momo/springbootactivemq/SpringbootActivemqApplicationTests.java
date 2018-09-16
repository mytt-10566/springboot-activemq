package com.momo.springbootactivemq;

import com.momo.springbootactivemq.queue.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootActivemqApplicationTests {

    @Autowired
    private Producer producer;

    @Test
    public void queueTest() {
        Destination destination = new ActiveMQQueue("test.queue");
        for (int i = 0; i < 100; i++) {
            producer.sendMessage(destination, "测试信息，i=" + i);
        }
    }
}
