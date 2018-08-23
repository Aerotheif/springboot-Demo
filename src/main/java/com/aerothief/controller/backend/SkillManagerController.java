package com.aerothief.controller.backend;

import com.aerothief.common.ServerResponse;
import com.aerothief.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.List;

@Controller
@RequestMapping("/backend/skill")
public class SkillManagerController {
    @Autowired
    private SkillService skillService;
    @ResponseBody
    @RequestMapping(value="getAllSkill.do")
    public ServerResponse getAllSkill(){
        return skillService.getAllSkill();
    }
    @ResponseBody
    @RequestMapping(value="createSkill.do")
    public ServerResponse createSkill(ServletRequest request){
        return skillService.insertSkill(request);
    }

}
