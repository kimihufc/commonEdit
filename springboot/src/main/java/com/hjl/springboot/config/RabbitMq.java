package com.hjl.springboot.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: HJL
 * @create: 2018-12-27 20:12
 * */


@Configuration
public class RabbitMq {

    @Value("${rabbit.host}")
    private String rabbitMqHost;

    @Value("${rabbit.port}")
    private Integer rabbitMqPort;

    @Value("${rabbit.username}")
    private String rabbitMqName;

    @Value("${rabbit.password}")
    private String rabbitMqPass;

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(rabbitMqHost);
        factory.setPort(rabbitMqPort);
        factory.setUsername(rabbitMqName);
        factory.setPassword(rabbitMqPass);
        return factory;
    }

    @Bean
    public RabbitTemplate getrabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        return rabbitTemplate;
    }

    @Bean
    public RabbitGateway getRabbitGateway(){
        RabbitGateway rabbitGateway = new RabbitGateway();
        rabbitGateway.setConnectionFactory(connectionFactory());
        rabbitGateway.setRabbitTemplate(getrabbitTemplate());
        return rabbitGateway;
    }

}
