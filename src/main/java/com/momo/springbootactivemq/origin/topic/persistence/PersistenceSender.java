package com.momo.springbootactivemq.origin.topic.persistence;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PersistenceSender {

    public static void main(String[] args) throws Exception {
        String brokerURL = "tcp://activemq01.momo.com:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);

        Connection connection = connectionFactory.createConnection();

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createTopic("origin.topic.persistence");

        MessageProducer producer = session.createProducer(destination);
        // 持久化发送模式（所有消息都持久化）
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        connection.start();

        // text消息
        sendTextMessage(session, producer);

        session.commit();
        session.close();
        connection.close();
    }

    private static void sendTextMessage(Session session, MessageProducer producer) throws Exception {
        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("发送消息，i=" + i);
            // message上设置传递模式
//            message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);

            // 生产者发送消息
            producer.send(message);

            // 为每条消息设置传递模式，优先级以及过期时间
//            producer.send(message, DeliveryMode.PERSISTENT, 4, 0);
        }
    }
}
