package com.CraftIQ.CraftIQ.dto;

import com.CraftIQ.CraftIQ.entity.Feedback;
import lombok.Data;
import org.modelmapper.ModelMapper;
import com.CraftIQ.CraftIQ.entity.SkillPosts;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class SkillPostsDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private String category;
    private String tags;
    private String imageUrl;
    // List of feedbacks for this post
    private List<FeedbackDto> feedbacks = new ArrayList<>();

    public SkillPosts toEntity(ModelMapper mapper) {
        SkillPosts post = mapper.map(this, SkillPosts.class);

        // Manually map feedbacks to avoid cyclic references
        if (this.feedbacks != null) {
            List<Feedback> feedbackEntities = this.feedbacks.stream().map(dto -> {
                Feedback feedback = dto.toEntity(mapper);
                feedback.setSkillPost(post); // Set the reverse link
                return feedback;
            }).collect(Collectors.toList());

            post.setFeedbacks(feedbackEntities);
        }

        return post;
    }
}

