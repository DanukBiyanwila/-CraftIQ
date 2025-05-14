package com.CraftIQ.CraftIQ.service.Impl;

import com.CraftIQ.CraftIQ.dto.FeedbackDto;
import com.CraftIQ.CraftIQ.entity.Feedback;
import com.CraftIQ.CraftIQ.entity.SkillPosts;
import com.CraftIQ.CraftIQ.entity.User;
import com.CraftIQ.CraftIQ.exception.NotFoundException;
import com.CraftIQ.CraftIQ.repository.FeedbackRepository;
import com.CraftIQ.CraftIQ.repository.SkillPostsRepository;
import com.CraftIQ.CraftIQ.repository.UserRepository;
import com.CraftIQ.CraftIQ.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ModelMapper mapper;
    private final SkillPostsRepository skillPostsRepository;
    private final UserRepository userRepository;

    // Create Feedback
    @Override
    public FeedbackDto createFeedback(FeedbackDto feedbackDto) {
        // 1. Basic field mapping
        Feedback feedback = mapper.map(feedbackDto, Feedback.class);

        // 2. Manual relationship resolution
        User user = userRepository.findById(feedbackDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + feedbackDto.getUserId()));
        SkillPosts post = skillPostsRepository.findById(feedbackDto.getSkillPostId())
                .orElseThrow(() -> new RuntimeException("SkillPost not found with ID: " + feedbackDto.getSkillPostId()));

        feedback.setUser(user);
        feedback.setSkillPost(post);
        feedback.setCreatedAt(LocalDateTime.now());

        // 3. Save and return
        Feedback savedFeedback = feedbackRepository.save(feedback);
        return savedFeedback.toDto(mapper);
    }



    // Get all Feedback
    @Override
    public List<FeedbackDto> getAllFeedback() {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        return feedbackList.stream()
                .map(feedback -> mapper.map(feedback, FeedbackDto.class))
                .collect(Collectors.toList());
    }

    // Get Feedback by ID
    @Override
    public FeedbackDto getFeedbackById(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Feedback not found with ID: " + id));
        return mapper.map(feedback, FeedbackDto.class);
    }

    // Update Feedback
    @Override
    public FeedbackDto updateFeedback(Long id, FeedbackDto feedbackDto) {
        Feedback existingFeedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Feedback not found with ID: " + id));


        if (feedbackDto.getSkillPostId() != null) {
            SkillPosts skillPost = skillPostsRepository.findById(feedbackDto.getSkillPostId())
                    .orElseThrow(() -> new NotFoundException("SkillPost not found with ID: " + feedbackDto.getSkillPostId()));
            existingFeedback.setSkillPost(skillPost);
        }

        Feedback updatedFeedback = feedbackRepository.save(existingFeedback);
        return mapper.map(updatedFeedback, FeedbackDto.class);
    }

    // Delete Feedback
    @Override
    public Boolean deleteFeedback(Long id) {
        if (!feedbackRepository.existsById(id)) {
            throw new NotFoundException("Feedback not found with ID: " + id);
        }
        feedbackRepository.deleteById(id);
        return true;
    }
}
