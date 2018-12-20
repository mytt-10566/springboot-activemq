package com.momo.springbootactivemq.study.queue.transacted;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueReceiver {

    public static void main(String[] args) throws Exception {
        String brokerURL = "tcp://dev.sks.org:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("queue.test");

        // 创建消费者，并指定destination（同步消费）
        MessageConsumer consumer = session.createConsumer(destination);
        // 接收TextMessage
        receiveTextMessage(session, consumer);
        
        // 或注册一个消息监听器（异步消费）
        // receiveTextMessageListener(session);

        session.close();
        connection.close();
    }

    private static void receiveTextMessage(Session session, MessageConsumer consumer) throws Exception {
        int i = 0;
        while (i < 3) {
            i++;
            TextMessage message = (TextMessage) consumer.receive();
            // 提交，确认消费成功
            session.commit();
            System.out.println("收到TextMessage：" + message.getText());
        }
    }

    private static void receiveTextMessageListener(Session session) throws Exception {
        session.setMessageListener(message -> {
            try {
                TextMessage textMessage = (TextMessage) message;
                // 提交，确认消费成功
                session.commit();
                System.out.println("收到TextMessage：" + textMessage.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    

}
