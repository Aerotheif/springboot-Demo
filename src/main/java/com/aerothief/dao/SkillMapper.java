package com.aerothief.dao;

import com.aerothief.model.Skill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Skill record);

    int insertSelective(Skill record);

    Skill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Skill record);

    int updateByPrimaryKey(Skill record);

    List<Skill> selectAllSkill();

    List<Skill> selectSkills(int fatherId);
}