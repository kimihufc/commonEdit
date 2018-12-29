package com.hjl.springboot.config;

import org.springframework.amqp.rabbit.core.RabbitGatewaySupport;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.UUID;

/**
 * @author: HJL
 * @create: 2018-12-29 16:20
 */
public class RabbitGateway extends RabbitGatewaySupport {

    public void send(String queue, Object o) {
        getRabbitTemplate().convertAndSend(queue, o, message -> {
            message.getMessageProperties().setCorrelationIdString(UUID.randomUUID().toString());
            return message;
        });
    }


    public void sendWithTransactionAware(String queue, Object o) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    send(queue, o);
                }
            });
        }
    }

}
