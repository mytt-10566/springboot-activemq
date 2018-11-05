package com.momo.springbootactivemq.study.origin.topic.persistence;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PersistenceReceiver {

    // 一定要先运行一次，等于向消息服务中间件注册这个消费者，然后再运行客户端发送信息
    // 这个时候，无论消费者是否在线，都会接收到；不在线的话，下次连接的时候，会把没有接收过的消息都接收下来
    public static void main(String[] args) throws Exception {
        String brokerURL = "tcp://activemq01.momo.com:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = connectionFactory.createConnection();

        // 连接上设置消费者id，用于识别消费者
        connection.setClientID("client01");

        final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Topic destination = session.createTopic("origin.topic.persistence");
        // 创建TopicSubscriber来订阅，name随便写
        TopicSubscriber consumer = session.createDurableSubscriber(destination, "T1");
        connection.start();

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
            session.commit();
            System.out.println("收到TextMessage：" + textMessage.getText());
            message = consumer.receive(1000L);
        }
    }

}
