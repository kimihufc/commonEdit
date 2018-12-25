package com.hjl.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author: HJL
 * @create: 2018-12-25 10:02
 */
@Configuration
public class RedisAccess {

    @Autowired
    private RedisTemplate redisTemplate;

    public void set(String key,String val){
        redisTemplate.opsForValue().set(key,val);
    }

    public void setTimeOut(String key,String val,Long time){
        redisTemplate.opsForValue().set(key,val,time, TimeUnit.SECONDS);
    }

    public String get(String key){
        return redisTemplate.opsForValue().get(key).toString();
    }

    public Boolean setNx(String key,String val,Long time){
        Boolean absent = redisTemplate.opsForValue().setIfAbsent(key, val);
        return absent;
    }

}
