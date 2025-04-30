package com.CraftIQ.CraftIQ.dto;

import com.CraftIQ.CraftIQ.entity.Feedback;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Data
public class FeedbackDto {

    private Long id;
    private String comment;
    private String author;
    private LocalDateTime createdAt;
    private String likeCount;

    public Feedback toEntity(ModelMapper mapper){return mapper.map(this, Feedback.class);}
}
