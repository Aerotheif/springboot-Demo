package com.aerothief.service.impl;

import com.aerothief.common.ServerResponse;
import com.aerothief.dao.SkillMapper;
import com.aerothief.model.Skill;
import com.aerothief.service.SkillService;
import com.aerothief.vo.SkillJsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import java.util.List;
@Service(value = "skillService")
public class SkillServiceImpl implements SkillService {
    @Autowired
    private SkillMapper skillMapper;
    @Override
    public ServerResponse getAllSkill() {
        List<Skill> skillList=skillMapper.selectAllSkill();
        for(Skill skill:skillList){
            if(skill.getName().length()>=15){
                skill.setName(skill.getName().substring(0,13)+"...");
            }
        }
        return ServerResponse.createBySuccess(skillList);
    }

    @Override
    public ServerResponse insertSkill(ServletRequest request) {
        String name=request.getParameter("name");
        String value=request.getParameter("value");
        int fatherId= Integer.parseInt(request.getParameter("father_id"));
        int step= Integer.parseInt(request.getParameter("step"));
        Skill skill=new Skill();
        skill.setFatherId(fatherId);
        skill.setName(name);
        skill.setStep(step);
        skill.setValue(value);
        return ServerResponse.createBySuccess(skillMapper.insertSelective(skill));
    }



    @Override
    public ServerResponse getTree() {
        Skill skill=skillMapper.selectSkills(-1).get(0);
        SkillJsonObject skillJsonObject=new SkillJsonObject(skill);
        skillJsonObject=addChildrens(skillJsonObject);
        skillJsonObject=setValues(skillJsonObject);
        return ServerResponse.createBySuccess(skillJsonObject);
    }
    private SkillJsonObject addChildrens(SkillJsonObject skillJsonObject){
        List<SkillJsonObject> skillJsonObjectList=SkillJsonObject.getSkillJsonObjectList(skillMapper.selectSkills(skillJsonObject.getId()));
        if(skillJsonObjectList.size()==0){
            return skillJsonObject;
        }else{
            skillJsonObject.setChildren(skillJsonObjectList);
            for(SkillJsonObject temp:skillJsonObject.getChildren()){
                temp=addChildrens(temp);
            }
        }
        return skillJsonObject;
    }
    private SkillJsonObject setValues(SkillJsonObject skillJsonObject){
        if(skillJsonObject.getChildren().size()!=0){
            skillJsonObject.setValue(SkillJsonObject.getSkillJsonObjectValue(skillJsonObject));
            for(SkillJsonObject temp:skillJsonObject.getChildren()){
                temp=setValues(temp);
            }
            return skillJsonObject;
        }else{
            return skillJsonObject;
        }
    }
}
