package com.momo.springbootactivemq.study.origin.queue.common;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;

public class QueueReceiverClientAck {

    public static void main(String[] args) throws Exception {
        String brokerURL = "tcp://activemq01.momo.com:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // JMSXPropertyNames
        getJMSXProperty(connection);

        final Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
        Destination destination = session.createQueue("origin.queue");

        MessageConsumer consumer = session.createConsumer(destination);

        // TextMessage
        receiveTextMessage(consumer);

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

    private static void receiveTextMessage(MessageConsumer consumer) throws Exception {
        int i = 0;
        while (i < 3) {
            TextMessage message = (TextMessage) consumer.receive();
            if (i == 2) {
                // 确认收到通知
                message.acknowledge();
            }
            System.out.println("收到TextMessage：" + message.getText());
            i++;
        }
    }

}
