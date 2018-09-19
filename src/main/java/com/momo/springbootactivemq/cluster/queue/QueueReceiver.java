package com.momo.springbootactivemq.cluster.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueReceiver {

    public static void main(String[] args) throws Exception {
        String brokerURL = "failover:(tcp://activemq01.momo.com:53531,tcp://activemq01.momo.com:53532,tcp://activemq01.momo.com:53533)?randomize=false";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("cluster.queue");

        MessageConsumer consumer = session.createConsumer(destination);

        // TextMessage
        receiveTextMessage(session, consumer);

        session.close();
        connection.close();
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

}
