package com.aerothief.vo;

import com.aerothief.model.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SkillJsonObject {
    private int step;
    private int id;
    private int fatherId;
    private String name;
    private int value;
    private List<SkillJsonObject> children;

    public static int getSkillJsonObjectValue(SkillJsonObject skillJsonObject){
       int reqValue=0;
       if(skillJsonObject.getChildren().size()==0){
           return skillJsonObject.getValue();
       }else{
           for(SkillJsonObject temp:skillJsonObject.getChildren()){
               reqValue+=SkillJsonObject.getSkillJsonObjectValue(temp);
           }
       }
       return reqValue;

    }
    public SkillJsonObject(Skill skill) {
//        if(skill.getName().length()>=7){
//            skill.setName(skill.getName().substring(0,7)+"...");
//        }
        this.step = skill.getStep();
        this.id = skill.getId();
        this.fatherId = skill.getFatherId();
        this.name = skill.getName();
        this.value =skill.getValue().equals("")?0:Integer.parseInt(skill.getValue());
        this.children=new ArrayList<>();
    }
    public static List<SkillJsonObject> getSkillJsonObjectList(List<Skill> skillList){
        List<SkillJsonObject> reqList=new ArrayList<>();
        for (Skill skill:skillList){
            reqList.add(new SkillJsonObject(skill));
        }
        return reqList;
    }
    public int getStep() {
        return step;
    }

    public int getId() {
        return id;
    }

    public int getFatherId() {
        return fatherId;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public List<SkillJsonObject> getChildren() {
        return children;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setChildren(List<SkillJsonObject> children) {
        this.children = children;
    }
}
