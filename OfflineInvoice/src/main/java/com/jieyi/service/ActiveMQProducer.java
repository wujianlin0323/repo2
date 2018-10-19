package com.jieyi.service;


import com.jieyi.configure.ActiveMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ActiveMQProducer {

    private Logger logger = LoggerFactory.getLogger(ActiveMQProducer.class);

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    private static ActiveMQProducer activeMQProducer;

    @PostConstruct
    public void init() {
        activeMQProducer = this;
        activeMQProducer.jmsMessagingTemplate = this.jmsMessagingTemplate;
    }

    @Async
    public void send(String message) {
        try {
            logger.info("message=" + message);

            this.jmsMessagingTemplate.convertAndSend(ActiveMQConfig.QUEUE_NAME, message);
            logger.info("message=" + message + "发送成功!");
        } catch (Exception e) {
            logger.error("message=" + message + "发送失败", e);
            logger.error(e.getMessage());
        }

    }

}


