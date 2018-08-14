package com.aerothief.controller;

import com.aerothief.model.User;
import com.aerothief.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Aerothief on 2018/08/14
 */

@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping(value = "/hello")
    public User test(){
        User user=userService.getUserByKey(1);
        return user;
    }


}
