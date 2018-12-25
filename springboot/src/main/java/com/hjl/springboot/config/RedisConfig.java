package com.hjl.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import redis.clients.jedis.Jedis;

/**
 * @author: HJL
 * @create: 2018-12-25 09:49
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private String redisPort;

    @Bean
    public RedisConnection getRedisConnect() {
        Jedis jedis = new Jedis(redisHost, Integer.parseInt(redisPort));
        RedisConnection redisConnection = new JedisConnection(jedis);
        return redisConnection;
    }

//    @Bean
//    public RedisTemplate getRedisTemplate() {
//        RedisTemplate redisTemplate = new RedisTemplate();
//        return redisTemplate;
//    }

}
