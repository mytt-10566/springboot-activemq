package com.momo.springbootactivemq.study.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueSender {

    public static void main(String[] args) throws Exception {
        String brokerURL = "failover:(tcp://activemq01.momo.com:53531,tcp://activemq01.momo.com:53532,tcp://activemq01.momo.com:53533)?randomize=false";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue("cluster.queue");

        MessageProducer producer = session.createProducer(destination);

        // text消息
        sendTextMessage(session, producer);

        session.commit();
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
