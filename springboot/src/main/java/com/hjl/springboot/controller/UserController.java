package com.hjl.springboot.controller;

import com.hjl.springboot.domain.User;
import com.hjl.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: HJL
 * @create: 2018-12-25 19:19
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户
     *
     * @param name
     * @param age
     * @return
     */
    @RequestMapping(value = "save")
    public String save(String name, Integer age) {
        userService.save(name, age);
        return "success";
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping(value = "findAll")
    public List<User> findAll() {
        return userService.findAll();
    }


}
