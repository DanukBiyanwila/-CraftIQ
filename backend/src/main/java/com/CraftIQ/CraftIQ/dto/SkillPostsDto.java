package com.CraftIQ.CraftIQ.dto;

import lombok.Data;
import org.modelmapper.ModelMapper;
import com.CraftIQ.CraftIQ.entity.SkillPosts;

import java.time.LocalDateTime;

@Data
public class SkillPostsDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private String category;
    private String tags;
    private String imageUrl;

    public SkillPosts toEntity(ModelMapper mapper) {
        return mapper.map(this, SkillPosts.class);
    }
}

