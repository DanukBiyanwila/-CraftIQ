package com.CraftIQ.CraftIQ.entity;

import com.CraftIQ.CraftIQ.dto.LearningPlansDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "learning_plans")
public class LearningPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "start_date", nullable = false)
    private java.time.LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private java.time.LocalDate endDate;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "status", nullable = false)
    private String status;  // For example: "In Progress", "Completed", "Pending"

    public LearningPlansDto toDto(ModelMapper mapper) {
        return mapper.map(this, LearningPlansDto.class);
    }
}

