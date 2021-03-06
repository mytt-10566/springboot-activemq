package com.momo.springbootactivemq.study.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueReceiver {

    public static void main(String[] args) throws Exception {
        String brokerURL = "tcp://dev.sks.org:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // 不开启本地事务，客户端手动确认
        final Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
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
            System.out.println("收到TextMessage：" + message.getText());
            // 确认消息，对应Session.CLIENT_ACKNOWLEDGE，否则对于该消息下次仍然能接收到（非持久化和持久化的区别是broker挂了，非持久化消息就收不到了）
            message.acknowledge();
        }
    }

    private static void receiveTextMessageListener(Session session) throws Exception {
        session.setMessageListener(message -> {
            try {
                TextMessage textMessage = (TextMessage) message;
                System.out.println("收到TextMessage：" + textMessage.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    

}
