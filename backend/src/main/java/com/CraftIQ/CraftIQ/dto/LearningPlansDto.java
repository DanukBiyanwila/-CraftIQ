package com.CraftIQ.CraftIQ.dto;

import com.CraftIQ.CraftIQ.entity.User;
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

    private Long userId;


    // Convert DTO to Entity
    public LearningPlans toEntity(ModelMapper mapper) {

        LearningPlans plan = mapper.map(this, LearningPlans.class);
        if (this.userId != null) {
            User user = new User();
            user.setId(this.userId); // set only ID for relation
            plan.setUser(user);
        }
        return plan;
    }
}
