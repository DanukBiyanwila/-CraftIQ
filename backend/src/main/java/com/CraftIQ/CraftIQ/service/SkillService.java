package com.CraftIQ.CraftIQ.service;

import com.CraftIQ.CraftIQ.dto.SkillDto;
import java.util.List;

public interface SkillService {
    SkillDto createSkill(SkillDto skillDto);
    List<SkillDto> getAllSkills();
    SkillDto getSkillById(Long id);
    SkillDto updateSkill(Long id, SkillDto skillDto);
    boolean deleteSkill(Long id);
}

