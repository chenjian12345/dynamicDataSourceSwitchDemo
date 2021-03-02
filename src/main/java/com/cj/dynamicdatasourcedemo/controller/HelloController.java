package com.cj.dynamicdatasourcedemo.controller;

import com.cj.dynamicdatasourcedemo.annotation.DynamicDataSourceSwitch;
import com.cj.dynamicdatasourcedemo.pojo.User;
import com.cj.dynamicdatasourcedemo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class HelloController {

    @Resource
    private UserService userService;

    @RequestMapping("/index")
    @DynamicDataSourceSwitch
    public List<User> sayHello(@RequestParam("customerCode") String customerCode)
    {
        return userService.findAll(customerCode);
    }
}