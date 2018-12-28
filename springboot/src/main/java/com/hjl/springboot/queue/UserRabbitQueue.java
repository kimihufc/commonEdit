package com.hjl.springboot.queue;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author: HJL
 * @create: 2018-12-27 20:40
 */
@Component
public class UserRabbitQueue {

    private final  static String USER_QUEUE="user_queue";

    @Bean
    public Queue createUserQueue(){
        return new Queue(USER_QUEUE);
    }

    @RabbitListener(queues = USER_QUEUE)
    public void dealQueue(Message message){
        System.out.println("------");
        System.out.println(message);
    }
}
