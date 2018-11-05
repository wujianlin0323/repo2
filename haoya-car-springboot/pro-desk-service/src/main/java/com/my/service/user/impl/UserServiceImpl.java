package com.my.service.user.impl;

import com.my.mapper.UserMapper;
import com.my.model.User;
import com.my.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by TXW55 on 2018.9.5
 */
@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserMapper userMapper;

    public User test(){
        return userMapper.selectByPrimaryKey(1L);
    }
}
