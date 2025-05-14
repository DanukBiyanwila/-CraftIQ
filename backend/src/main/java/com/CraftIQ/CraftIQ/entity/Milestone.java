package com.CraftIQ.CraftIQ.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Milestone {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "learning_plan_id")
    private LearningPlans learningPlan;
}

