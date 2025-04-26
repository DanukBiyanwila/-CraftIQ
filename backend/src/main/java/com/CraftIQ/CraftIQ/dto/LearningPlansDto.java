package com.CraftIQ.CraftIQ.dto;

import lombok.Data;
import org.modelmapper.ModelMapper;
import com.CraftIQ.CraftIQ.entity.LearningPlans;

@Data
public class LearningPlansDto {
    private Long id;
    private String title;
    private String description;
    private java.time.LocalDate startDate;
    private java.time.LocalDate endDate;
    private String author;
    private String status;

    // Convert DTO to Entity
    public LearningPlans toEntity(ModelMapper mapper) {
        return mapper.map(this, LearningPlans.class);
    }
}
