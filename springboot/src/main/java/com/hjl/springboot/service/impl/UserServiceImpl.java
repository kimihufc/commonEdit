package com.hjl.springboot.service.impl;

import com.hjl.springboot.dao.UserMapper;
import com.hjl.springboot.domain.User;
import com.hjl.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: HJL
 * @create: 2018-12-25 18:19
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(String name, Integer age) {
        User param = new User();
        param.setAge(age);
        param.setName(name);
        userMapper.insertSelective(param);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }
}
