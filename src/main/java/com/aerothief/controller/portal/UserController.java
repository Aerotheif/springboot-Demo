package com.aerothief.controller.portal;

import com.aerothief.common.Const;
import com.aerothief.common.ServerResponse;
import com.aerothief.model.User;
import com.aerothief.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Aerothief on 2018/08/14
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 前台登录接口
     * @param request
     * @param session
     * @return
     */
    //用来对返回值对象反序列化
    @ResponseBody
    @RequestMapping(value="login.do",method = RequestMethod.POST)
    public ServerResponse login(ServletRequest request, HttpSession session){
        ServerResponse serverResponse=userService.login(request);
        if(serverResponse.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,serverResponse.getData());
        }
        return serverResponse;
    }


}
