package com.aerothief.controller.portal;

import com.aerothief.common.ServerResponse;
import com.aerothief.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/skill")
public class SkillController {
    @Autowired
    private SkillService skillService;
    @ResponseBody
    @RequestMapping(value="skillJson.do")
    public ServerResponse getSkillJson(HttpSession session){
        ServerResponse serverResponse=skillService.getTree();
        return serverResponse;
    }
}
