package com.aerothief.controller.portal;

import com.aerothief.common.Const;
import com.aerothief.common.ServerResponse;
import com.aerothief.model.User;
import com.aerothief.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
     * 用户登录
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

    /**
     * 用户名检验
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkUsernameExist.do",method = RequestMethod.POST)
    public ServerResponse checkUsername(ServletRequest request){
        return userService.checkUsernameExist(request);
    }

    /**
     * 注册
     * @param request
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value="register.do",method = RequestMethod.POST)
    public ServerResponse register(ServletRequest request,HttpSession session){
        ServerResponse serverResponse=userService.registerExist(request);
        if(serverResponse.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,serverResponse.getData());
        }
        return serverResponse;
    }

    /**
     * 获取用户信息
     * @param request
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value="getUserInfo.do",method = RequestMethod.POST)
    public ServerResponse getUserInfo(ServletRequest request,HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user != null){
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登陆，无法获取当前用户信息");
    }

    /**
     * 用户登出
     * @param session
     * @return
     */
    @RequestMapping(value = "logout.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }
}
