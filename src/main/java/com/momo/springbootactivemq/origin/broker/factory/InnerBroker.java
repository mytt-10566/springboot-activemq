package com.momo.springbootactivemq.origin.broker.factory;

import org.apache.activemq.broker.BrokerService;

public class InnerBroker {

    // 本地启动一个ActiveMQ服务器实例
    // 需要连接tcp://127.0.0.1:61616 收发，无法页面访问
    public static void main(String[] args) throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://127.0.0.1:61616");
        brokerService.start();
    }
}
