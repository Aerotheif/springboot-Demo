package com.aerothief.service.impl;

import com.aerothief.dao.UserMapper;
import com.aerothief.model.User;
import com.aerothief.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public User getUserByKey(int key) {
        return userMapper.selectByPrimaryKey(key);
    }
}
