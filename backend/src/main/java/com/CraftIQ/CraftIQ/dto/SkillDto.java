package com.CraftIQ.CraftIQ.dto;

import lombok.Data;
import org.modelmapper.ModelMapper;
import com.CraftIQ.CraftIQ.entity.Skill;
import java.time.LocalDateTime;

@Data
public class SkillDto {
    private Long skillId;
    private String skillName;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Skill toEntity(ModelMapper mapper) {
        return mapper.map(this, Skill.class);
    }
}

