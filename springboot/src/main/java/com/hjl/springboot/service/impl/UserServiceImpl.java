package com.hjl.springboot.service.impl;

import com.hjl.springboot.aspect.DBRoute;
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
    @DBRoute(name = "one")
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    @DBRoute(name = "three")
    public User selectByName(String name) {
        User param = new User();
        param.setName(name);
        List<User> list = userMapper.select(param);
        return list==null?null:list.get(0);
    }
}
