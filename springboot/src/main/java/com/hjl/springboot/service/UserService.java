package com.hjl.springboot.service;

import com.hjl.springboot.domain.User;

import java.util.List;

/**
 * @author: HJL
 * @create: 2018-12-25 18:18
 */
public interface UserService {

    void save(String name,Integer age);

    List<User> findAll();
}
