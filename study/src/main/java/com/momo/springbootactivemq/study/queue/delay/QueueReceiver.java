package com.momo.springbootactivemq.study.queue.delay;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.CountDownLatch;

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

        // 或注册一个消息监听器（异步消费）
        receiveTextMessageListener(session);

        new CountDownLatch(1).await();
    }

    private static void receiveTextMessageListener(Session session) throws Exception {
        session.setMessageListener(message -> {
            try {
                TextMessage textMessage = (TextMessage) message;
                System.out.println("收到TextMessage：" + textMessage.getText());
                textMessage.acknowledge();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}
