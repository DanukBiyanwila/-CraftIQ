package com.CraftIQ.CraftIQ.entity;

import com.CraftIQ.CraftIQ.dto.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "skill_posts")
public class SkillPosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "tags")
    private String tags;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "skillPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbacks = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)  // Add this line for the relationship
    @JoinColumn(name = "user_id", nullable = false)  // Defines the column name for the foreign key
    private User user;

    @OneToMany(mappedBy = "skillPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();


    public SkillPostsDto toDto(ModelMapper mapper) {
        SkillPostsDto skillPostsDto = mapper.map(this, SkillPostsDto.class);
        // Map feedbacks to FeedbackDto list (avoid cyclic references)
        if (this.getFeedbacks() != null) {
            List<FeedbackDto> feedbackDtos = this.getFeedbacks().stream()
                    .map(feedback -> mapper.map(feedback, FeedbackDto.class))
                    .collect(Collectors.toList());
            skillPostsDto.setFeedbacks(feedbackDtos);
        }

        if (this.getLikes() != null) {
            List<LikeDto> likeDtos = this.getLikes().stream()
                    .map(like -> {
                        LikeDto dto = new LikeDto();
                        dto.setId(like.getId());
//                        dto.setUserId(like.getUser().getId());
                        dto.setSkillPostId(like.getSkillPost().getId());
                        return dto;
                    }).collect(Collectors.toList());
            skillPostsDto.setLikes(likeDtos);
        }
        return skillPostsDto;
    }
}
