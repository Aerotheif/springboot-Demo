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

    /**
     * 登陆
     * @param request
     * @return
     */
    @Override
    public ServerResponse login(ServletRequest request) {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        if(!this.checkUsernameExist(username)){
            return ServerResponse.createByErrorCodeMessage(1,"用户名不存在！");
        }
        User user=userMapper.selectLogin(username,md5Password);
        if(user!=null){
            user.setPassword(StringUtils.EMPTY);
            return ServerResponse.createBySuccess("登陆成功",user);
        }
        return ServerResponse.createByErrorCodeMessage(2,"密码错误!");
    }

    /**
     * 检查用户名是否存在
     * @param request
     * @return
     */
    @Override
    public ServerResponse checkUsernameExist(ServletRequest request) {
        String username=request.getParameter("username");
        if(this.checkUsernameExist(username)){
            return ServerResponse.createByErrorMessage("用户名已存在");
        }else{
            return ServerResponse.createBySuccess();
        }
    }

    /**
     * 用户注册
     * @param request
     * @return
     */
    @Override
    public ServerResponse<User> registerExist(ServletRequest request) {
        String username =request.getParameter("username");
        String password =request.getParameter("password");
        if(this.checkUsernameExist(username)){
            return ServerResponse.createByErrorMessage("用户名已存在");
        }else{
            User user=new User();
            user.setUsername(username);
            user.setPassword(MD5Util.MD5EncodeUtf8(password));
            int flag=userMapper.insert(user);
            if(flag!=1){
                return ServerResponse.createByErrorMessage("系统异常");
            }else{
                user.setPassword(StringUtils.EMPTY);
                return ServerResponse.createBySuccess(user);
            }
        }
    }

    /**
     * 检查用户名是否存在 存在true 不存在false
     * @param username
     * @return
     */
    private Boolean checkUsernameExist(String username) {
        int resultCount =userMapper.checkUsername(username);
        if(resultCount!=0){
            return true;
        }else {
            return false;
        }
    }



}
