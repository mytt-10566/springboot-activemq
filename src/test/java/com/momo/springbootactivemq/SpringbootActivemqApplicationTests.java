package com.momo.springbootactivemq;

import com.momo.springbootactivemq.queue.test.TestProducer;
import com.momo.springbootactivemq.topic.test.Publisher;
import com.momo.springbootactivemq.vo.OrderJobBaseVO;
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
    private TestProducer producer;
    @Autowired
    private Publisher publisher;

    @Test
    public void queueTest() {
        Destination destination = new ActiveMQQueue("test.queue");
        for (int i = 0; i < 100; i++) {
            producer.sendMessage(destination, "测试信息，i=" + i);
        }
    }

    @Test
    public void testSendQyQueueJsonMessage() {
        Destination destination = new ActiveMQQueue("queue.order.scenery.notice111");

        OrderJobBaseVO jobData = new OrderJobBaseVO<>();
        jobData.setOrderId(6040000012L);
        jobData.setMainOrderId(1000923L);
        jobData.setType(2);
        producer.sendJsonMessage(destination, jobData);
    }

    @Test
    public void subscriber() {
        for (int i = 0; i < 10; i++) {
            publisher.publish("test.topic", "Topic Message " + i);
        }
    }
}
