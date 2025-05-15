package com.CraftIQ.CraftIQ.dto;

import com.CraftIQ.CraftIQ.entity.Feedback;
import com.CraftIQ.CraftIQ.entity.LearningPlans;
import com.CraftIQ.CraftIQ.entity.SkillPosts;
import com.CraftIQ.CraftIQ.entity.User;
import jakarta.persistence.Column;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String fullName;
    private String tpNum;
    private String address;
    private String category;
    private String email;
    private String password;
    private String bio;
    private String profilePicture;
    private String interests;



    private String imageBase64;

    private Set<UserSummaryDto> followers = new HashSet<>();
    private Set<UserSummaryDto> following = new HashSet<>();

    private Set<FeedbackDto> feedbacks = new HashSet<>();

    private Set<SkillPostsDto> skillPosts = new HashSet<>();

    private Set<LearningPlansDto> learningPlans = new HashSet<>();



    public User toEntity(ModelMapper mapper) {
        User user = mapper.map(this, User.class);

        // Map feedbacks if present
        if (this.feedbacks != null) {
            Set<Feedback> feedbackEntities = this.feedbacks.stream().map(dto -> {
                Feedback feedback = mapper.map(dto, Feedback.class);
                feedback.setUser(user); // important to establish ownership
                return feedback;
            }).collect(Collectors.toSet());

            user.setFeedbacks(feedbackEntities);
        }

        // Map skillPosts if present
        if (this.skillPosts != null) {
            Set<SkillPosts> skillPostEntities = this.skillPosts.stream().map(dto -> {
                SkillPosts post = dto.toEntity(mapper);
                post.setUser(user); // establish ownership of skill post by user
                return post;
            }).collect(Collectors.toSet());

            user.setSkillPosts(skillPostEntities); // link the skill posts to the user
        }

        // Map learning plans if present
        if (this.learningPlans != null) {
            Set<LearningPlans> learningPlanEntities = this.learningPlans.stream().map(dto -> {
                LearningPlans plan = mapper.map(dto, LearningPlans.class);
                plan.setUser(user); // establish ownership of the plan
                return plan;
            }).collect(Collectors.toSet());

            user.setLearningPlans(learningPlanEntities);
        }

        if (this.imageBase64 != null) {
            user.setImageData(Base64.getDecoder().decode(this.imageBase64));
        }


        return user;
    }
}

