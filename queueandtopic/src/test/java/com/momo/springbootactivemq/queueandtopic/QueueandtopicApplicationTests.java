package com.momo.springbootactivemq.queueandtopic;

import com.momo.springbootactivemq.queueandtopic.queue.Producer;
import com.momo.springbootactivemq.queueandtopic.topic.Publisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueueandtopicApplicationTests {
    @Autowired
    private Producer producer;
    @Autowired
    private Publisher publisher;

    @Test
    public void sendQueueMessage() {
        for (int i = 0; i < 50; i++) {
            producer.sendTextMessage("queue message, i = " + i);
        }
    }
    
    @Test
    public void sendTransQueueMessage() {
        for (int i = 0; i < 50; i++) {
            producer.sendTransTextMessage("queue message, i = " + i);
        }
    }

    @Test
    public void sendTopicMessage() {
        for (int i = 0; i < 50; i++) {
            publisher.sendTextMessage("topic message, i = " + i);
        }
    }

}
