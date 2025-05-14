package com.CraftIQ.CraftIQ.dto;

import com.CraftIQ.CraftIQ.entity.Feedback;
import com.CraftIQ.CraftIQ.entity.SkillPosts;
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

    private Long skillPostId;

    public Feedback toEntity(ModelMapper mapper) {
        Feedback feedback = mapper.map(this, Feedback.class);

        if (this.skillPostId != null) {
            SkillPosts post = new SkillPosts();
            post.setId(this.skillPostId);
            feedback.setSkillPost(post);
        }

        return feedback;
    }

}
