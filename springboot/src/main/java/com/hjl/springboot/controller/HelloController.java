package com.hjl.springboot.controller;

import com.hjl.springboot.config.RabbitGateway;
import com.hjl.springboot.config.RedisAccess;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: HJL
 * @create: 2018-12-18 17:06
 */
@RestController
public class HelloController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisAccess redisAccess;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitGateway rabbitGateway;

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "redis")
    public String testRedis() {
        redisTemplate.opsForValue().set("name", "hello");
        return redisTemplate.opsForValue().get("name").toString();
    }

    @RequestMapping(value = "redisSet")
    public void setRedis(String key,String val){
        redisAccess.setNx(key,val,60L);
    }

    @GetMapping(value = "redisGet")
    public String getRedis(String key){
        String result = redisAccess.get(key);
        return result;
    }

    @RequestMapping("rabbitmq")
    public String rabbitmq(){
      rabbitTemplate.send("user_queue", new Message("123".getBytes(),new MessageProperties()));
        return "";
    }
    @RequestMapping("rabbitmqgateway")
    public String rabbitmqgateway(){
        rabbitGateway.send("user_queue", new Message("123".getBytes(),new MessageProperties()));
        return "success";
    }

}
