package com.CraftIQ.CraftIQ.dto;

import com.CraftIQ.CraftIQ.entity.LearningPlans;
import lombok.Data;

@Data
public class MilestoneDto {

    private Long id;
    private String name;
    private boolean completed;
}
