package com.momo.springbootactivemq.queueandtopic.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQConnectionFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Session;

/**
 * @author: MQG
 * @date: 2018/11/5
 */
@Configuration
@EnableJms
public class ActiveMQConfig {

    @Configuration
    static class MyActiveMQConnectionFactoryCustomizer implements ActiveMQConnectionFactoryCustomizer {
        @Bean
        public RedeliveryPolicy redeliveryPolicy() {
            RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
            // 是否在每次尝试重新发送失败后,增长这个等待时间
            redeliveryPolicy.setUseExponentialBackOff(true);
            // 重发次数,默认为6次   这里设置为10次
            redeliveryPolicy.setMaximumRedeliveries(10);
            // 重发时间间隔,默认为1秒
            redeliveryPolicy.setInitialRedeliveryDelay(1);
            // 第一次失败后重新发送之前等待500毫秒,第二次失败再等待500 * 2毫秒,这里的2就是value
            redeliveryPolicy.setBackOffMultiplier(2);
            // 是否避免消息碰撞
            redeliveryPolicy.setUseCollisionAvoidance(false);
            // 设置重发最大拖延时间-1 表示没有拖延只有UseExponentialBackOff(true)为true时生效
            redeliveryPolicy.setMaximumRedeliveryDelay(-1);
            return redeliveryPolicy;
        }

        @Override
        public void customize(ActiveMQConnectionFactory factory) {
            factory.setRedeliveryPolicy(redeliveryPolicy());
        }
    }

    @Bean("jmsTemplate")
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);// 进行持久化配置 1表示非持久化，2表示持久化
        jmsTemplate.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);// 客户端签收模式
        jmsTemplate.setConnectionFactory(connectionFactory);
        return jmsTemplate;
    }

    @Bean("transactedJmsTemplate")
    public JmsTemplate transactedJmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);// 进行持久化配置 1表示非持久化，2表示持久化
        jmsTemplate.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);// 事务
        jmsTemplate.setSessionTransacted(true);
        jmsTemplate.setConnectionFactory(connectionFactory);
        return jmsTemplate;
    }

    @Bean("queueListenerFactory")
    public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(false);
        factory.setSessionTransacted(false);
        factory.setConcurrency("5");
        return factory;
    }

    @Bean("topicListenerFactory")
    public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }
}
