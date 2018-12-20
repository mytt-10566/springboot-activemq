package com.momo.springbootactivemq.study.queue.transacted;

import org.apache.activemq.ActiveMQConnectionFactory;

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

        // 创建session，开启事务，自动确认
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        // 创建destination
        Destination destination = session.createQueue("queue.test");
        // 创建生产者，并设置创建destination
        MessageProducer producer = session.createProducer(destination);
        // 消息持久化，默认持久化
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        // 创建并发送text消息
        sendTextMessage(session, producer);

        // 提交
        session.commit();
        
        // 关闭所有资源（session、connection）
        session.close();
        connection.close();
    }

    private static void sendTextMessage(Session session, MessageProducer producer) throws Exception {
        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("发送消息，i=" + i);
            // 生产者发送消息
            producer.send(message);
        }
    }

}
