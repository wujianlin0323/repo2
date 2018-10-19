package com.jieyi.configure;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import javax.jms.Queue;


@Configuration
@EnableJms
public class ActiveMQConfig {

    public static final String QUEUE_NAME = "EMAIL_MSG";

    public static final String ERROR_QUEUE_NAME = "ERROR";

    @Bean
    public Queue Queue() {
        return new ActiveMQQueue(QUEUE_NAME);
    }

    @Bean
    public Queue ErrorQueue() {
        return new ActiveMQQueue(ERROR_QUEUE_NAME);
    }

//TODO 临时注掉
}
