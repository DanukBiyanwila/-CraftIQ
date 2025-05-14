package com.CraftIQ.CraftIQ.dto;

import com.CraftIQ.CraftIQ.entity.Feedback;
import com.CraftIQ.CraftIQ.entity.User;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String bio;
    private String profilePicture;
    private String interests;

    private Set<UserSummaryDto> followers = new HashSet<>();
    private Set<UserSummaryDto> following = new HashSet<>();

    private Set<FeedbackDto> feedbacks = new HashSet<>();


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

        return user;
    }
}

