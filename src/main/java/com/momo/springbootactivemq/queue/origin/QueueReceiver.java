package com.momo.springbootactivemq.queue.origin;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueReceiver {

    public static void main(String[] args) throws Exception {
        String brokerURL = "tcp://activemq01.momo.com:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("origin.queue");

        MessageConsumer consumer = session.createConsumer(destination);
        int i = 0;
        while (i < 3) {
            i++;
            TextMessage message = (TextMessage) consumer.receive();
            session.commit();
            System.out.println("收到消息：" + message.getText());
        }

        session.close();
        connection.close();
    }
}
