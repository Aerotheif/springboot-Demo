package com.aerothief.service.impl;

import com.aerothief.common.ServerResponse;
import com.aerothief.dao.UserMapper;
import com.aerothief.model.User;
import com.aerothief.service.UserService;
import com.aerothief.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public ServerResponse login(ServletRequest request) {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        int resultCount =userMapper.checkUsername(username);
        if(resultCount ==0){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        User user=userMapper.selectLogin(username,md5Password);
        if(user!=null){
            user.setPassword(StringUtils.EMPTY);
            return ServerResponse.createBySuccess("登陆成功",user);
        }
        return ServerResponse.createByErrorMessage("密码错误");
    }

}
