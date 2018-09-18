package com.momo.springbootactivemq.queue.origin.common;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueSender {

    public static void main(String[] args) throws Exception {
        String brokerURL = "tcp://activemq01.momo.com:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue("origin.queue");

        MessageProducer producer = session.createProducer(destination);

        // text消息
//        sendTextMessage(session, producer);
        // map消息
        sendMapMessage(session, producer);

        session.commit();
        session.close();
        connection.close();
    }

    private static void sendTextMessage(Session session, MessageProducer producer) throws Exception {
        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("发送消息，i=" + i);
            Thread.sleep(1000);
            // 生产者发送消息
            producer.send(message);
        }
    }

    private static void sendMapMessage(Session session, MessageProducer producer) throws Exception {
        for (int i = 0; i < 3; i++) {
            MapMessage message = session.createMapMessage();
            message.setString("map message", "map message " + i);
            message.setStringProperty("extra", "extra message " + i);

            // 生产者发送消息
            producer.send(message);
        }
    }
}
