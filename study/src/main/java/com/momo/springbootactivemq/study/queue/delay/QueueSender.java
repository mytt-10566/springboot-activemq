package com.momo.springbootactivemq.study.queue.delay;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;

public class QueueSender {

    public static void main(String[] args) throws Exception {
        // 创建连接工厂
        String brokerURL = "tcp://dev.sks.org:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        // 创建连接
        Connection connection = connectionFactory.createConnection();
        // 启动
        connection.start();

        // 创建session，不开启事务，自动确认
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建destination
        Destination destination = session.createQueue("queue.test");
        // 创建生产者，并设置创建destination
        MessageProducer producer = session.createProducer(destination);
        // 消息持久化，默认持久化
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        // 创建并发送text消息
        sendMessageDelayTextMessage(session, producer);

        // 关闭所有资源（session、connection）
        session.close();
        connection.close();
    }

    // 官方文档：http://activemq.apache.org/delay-and-schedule-message-delivery.html
    // 需要配置xml中broker元素的schedulerSupport属性
    private static void sendMessageDelayTextMessage(Session session, MessageProducer producer) throws Exception {
        long time = 6 * 1000;// 延时1min
        long period = 2 * 1000;// 每次投递间隔10s
        int repeat = 6;// 重复投递6次
        
        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("发送消息，i=" + i);
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, period);
            message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeat);
            // 生产者发送消息
            producer.send(message);
        }
    }

    private static void sendCornDelayTextMessage(Session session, MessageProducer producer) throws Exception {
        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("发送消息，i=" + i);
            // 设置CORN
            message.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_CRON, "0 * * * *");
            // 生产者发送消息
            producer.send(message);
        }
    }

    private static void sendCornAndMessageDelayTextMessage(Session session, MessageProducer producer) throws Exception {
        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("发送消息，i=" + i);
            // CRON调度优先于使用消息延迟 - 但是，如果使用CRON条目设置repeat和period，则ActiveMQ调度程序将在每次CRON条目触发时安排消息的投递。 
            // 用例子更容易解释。 假设希望将消息传递10次，每条消息之间有一秒钟的延迟，并且希望每小时发生一次 - 可以这样做：
            message.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_CRON, "0 * * * *");
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 1000);
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, 1000);
            message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, 9);
            // 生产者发送消息
            producer.send(message);
        }
    }

}
