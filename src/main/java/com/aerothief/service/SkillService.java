package com.aerothief.service;

import com.aerothief.common.ServerResponse;
import com.aerothief.model.Skill;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface SkillService {
    ServerResponse getAllSkill();
    ServerResponse insertSkill(ServletRequest request);
    ServerResponse getTree();
}
