package com.momo.springbootactivemq.config.amq;

import org.springframework.jms.core.JmsTemplate;

public class JmsTemplateWrapper {
    private JmsTemplate topicJmsTemplate;
    private JmsTemplate queueJmsTemplate;

    public JmsTemplate getTopicJmsTemplate() {
        return topicJmsTemplate;
    }

    public void setTopicJmsTemplate(JmsTemplate topicJmsTemplate) {
        this.topicJmsTemplate = topicJmsTemplate;
    }

    public JmsTemplate getQueueJmsTemplate() {
        return queueJmsTemplate;
    }

    public void setQueueJmsTemplate(JmsTemplate queueJmsTemplate) {
        this.queueJmsTemplate = queueJmsTemplate;
    }
}
