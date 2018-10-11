/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Description:BrokerFactory.java
 */
package com.momo.springbootactivemq.origin.broker.inner;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

import java.net.URI;

/**
 * @author: MQG
 * @date: 2018/9/19
 */
public class BrokerFactoryService {

    public static void main(String[] args) throws Exception {
        String uri = "properties:broker/broker.properties";
        BrokerService broker = BrokerFactory.createBroker(new URI(uri));
        broker.addConnector("tcp://127.0.0.1:61616");
        broker.start();
    }
}
