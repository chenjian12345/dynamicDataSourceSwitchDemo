package com.cj.dynamicdatasourcedemo.service;

import com.cj.dynamicdatasourcedemo.dao.UserRepository;
import com.cj.dynamicdatasourcedemo.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 用户
 * @Author: chenj
 * @Date: 2021/3/1
 **/
@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    public List<User> findAll(String customerCode)
    {
        return userRepository.findAll();
    }
}
