package com.momo.springbootactivemq.queue.origin;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;

public class QueueReceiver {

    public static void main(String[] args) throws Exception {
        String brokerURL = "tcp://activemq01.momo.com:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // JMSXPropertyNames
        getJMSXProperty(connection);

        final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("origin.queue");

        MessageConsumer consumer = session.createConsumer(destination);

        // TextMessage
//        receiveTextMessage(session, consumer);
        // MapMessage
        receiveMapMessage(session, consumer);

        session.close();
        connection.close();
    }

    private static void getJMSXProperty(Connection connection) throws Exception {
        Enumeration jmsxPropertyNames = connection.getMetaData().getJMSXPropertyNames();
        while (jmsxPropertyNames.hasMoreElements()) {
            String name = (String) jmsxPropertyNames.nextElement();
            System.out.println("jmsx name=" + name);
        }
    }

    private static void receiveTextMessage(Session session, MessageConsumer consumer) throws Exception {
        int i = 0;
        while (i < 3) {
            i++;
            TextMessage message = (TextMessage) consumer.receive();
            session.commit();
            System.out.println("收到TextMessage：" + message.getText());
        }
    }

    private static void receiveMapMessage(Session session, MessageConsumer consumer) throws Exception {
        int i = 0;
        while (i < 3) {

            MapMessage message = (MapMessage) consumer.receive();
            session.commit();
            System.out.println("收到MapMessage：" + message.getString("map message"));
            System.out.println("extra message：" + message.getStringProperty("extra"));

            i++;
        }
    }
}
