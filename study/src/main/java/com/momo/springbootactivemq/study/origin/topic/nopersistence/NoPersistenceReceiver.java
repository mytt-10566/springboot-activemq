package com.momo.springbootactivemq.study.origin.topic.nopersistence;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class NoPersistenceReceiver {

    public static void main(String[] args) throws Exception {
        String brokerURL = "tcp://activemq01.momo.com:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("origin.topic");

        MessageConsumer consumer = session.createConsumer(destination);

        // TextMessage
        receiveTextMessage(session, consumer);

        session.commit();
        session.close();
        connection.close();
    }

    private static void receiveTextMessage(Session session, MessageConsumer consumer) throws Exception {
        Message message = consumer.receive();
        while (message != null) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("收到TextMessage：" + textMessage.getText());
            message = consumer.receive(1000L);
        }
    }

}
