package com.CraftIQ.CraftIQ.service.Impl;

import com.CraftIQ.CraftIQ.dto.SkillDto;
import com.CraftIQ.CraftIQ.entity.Skill;
import com.CraftIQ.CraftIQ.exception.NotFoundException;
import com.CraftIQ.CraftIQ.repository.SkillRepository;
import com.CraftIQ.CraftIQ.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final ModelMapper mapper;

    @Override
    public SkillDto createSkill(SkillDto skillDto) {
        Skill skill = mapper.map(skillDto, Skill.class);
        Skill savedSkill = skillRepository.save(skill);
        return mapper.map(savedSkill, SkillDto.class);
    }

    @Override
    public List<SkillDto> getAllSkills() {
        List<Skill> skills = skillRepository.findAll();
        return skills.stream().map(skill -> mapper.map(skill, SkillDto.class)).collect(Collectors.toList());
    }

    @Override
    public SkillDto getSkillById(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Skill not found with ID: " + id));
        return mapper.map(skill, SkillDto.class);
    }

    @Override
    public SkillDto updateSkill(Long id, SkillDto skillDto) {
        if (!skillRepository.existsById(id)) {
            throw new NotFoundException("Skill not found with ID: " + id);
        }
        Skill skill = mapper.map(skillDto, Skill.class);
        skill.setSkillId(id);
        Skill updatedSkill = skillRepository.save(skill);
        return mapper.map(updatedSkill, SkillDto.class);
    }

    @Override
    public boolean deleteSkill(Long id) {
        if (!skillRepository.existsById(id)) {
            throw new NotFoundException("Skill not found with ID: " + id);
        }
        skillRepository.deleteById(id);
        return true;
    }
}

