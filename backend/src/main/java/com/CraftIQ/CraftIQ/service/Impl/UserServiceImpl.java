package com.CraftIQ.CraftIQ.service.Impl;

import com.CraftIQ.CraftIQ.dto.UserDto;
import com.CraftIQ.CraftIQ.entity.Feedback;
import com.CraftIQ.CraftIQ.entity.SkillPosts;
import com.CraftIQ.CraftIQ.entity.User;
import com.CraftIQ.CraftIQ.exception.NotFoundException;
import com.CraftIQ.CraftIQ.repository.UserRepository;
import com.CraftIQ.CraftIQ.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    // Create User
    @Override
    public UserDto createUser(UserDto userDto) {
        // 1. Convert DTO to Entity
        User user = mapper.map(userDto, User.class);

        // 2. Handle followers
        if (userDto.getFollowers() != null && !userDto.getFollowers().isEmpty()) {
            Set<User> followers = userDto.getFollowers().stream()
                    .map(summary -> userRepository.findById(summary.getId())
                            .orElseThrow(() -> new RuntimeException("Follower not found with ID: " + summary.getId())))
                    .collect(Collectors.toSet());
            user.setFollowers(followers);
        }

        // 3. Handle following
        if (userDto.getFollowing() != null && !userDto.getFollowing().isEmpty()) {
            Set<User> following = userDto.getFollowing().stream()
                    .map(summary -> userRepository.findById(summary.getId())
                            .orElseThrow(() -> new RuntimeException("Following user not found with ID: " + summary.getId())))
                    .collect(Collectors.toSet());
            user.setFollowing(following);
        }

        // 4. Handle feedbacks (establish back-reference)
        if (userDto.getFeedbacks() != null && !userDto.getFeedbacks().isEmpty()) {
            Set<Feedback> feedbackEntities = userDto.getFeedbacks().stream()
                    .map(dto -> {
                        Feedback feedback = mapper.map(dto, Feedback.class);
                        feedback.setUser(user); // Establish the relationship
                        return feedback;
                    })
                    .collect(Collectors.toSet());

            user.setFeedbacks(feedbackEntities);
        }

        // 5. Handle skillPosts (establish relationship between user and skill posts)
        if (userDto.getSkillPosts() != null && !userDto.getSkillPosts().isEmpty()) {
            Set<SkillPosts> skillPostEntities = userDto.getSkillPosts().stream()
                    .map(dto -> {
                        SkillPosts skillPost = dto.toEntity(mapper);
                        skillPost.setUser(user); // Establish the relationship (user owns the post)
                        return skillPost;
                    })
                    .collect(Collectors.toSet());
            user.setSkillPosts(skillPostEntities); // Associate posts with the user
        }

        // 5. Save Entity
        User savedUser = userRepository.save(user);

        // 6. Return DTO
        return savedUser.toDto(mapper);
    }



    // Get all Users
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return new ArrayList<>();
        } else {
            return users.stream()
                    .map(user -> user.toDto(mapper)) // Make sure to call the toDto method here
                    .collect(Collectors.toList());
        }
    }


    // Get User by ID
    @Override
    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get().toDto(mapper); // Use the same custom DTO mapping
        } else {
            throw new NotFoundException("User not found with ID: " + id);
        }
    }


    // Update User by ID
    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found with ID: " + id);
        }

        // Retrieve the existing user from the database
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));

        // Map the other fields from DTO to Entity
        User user = mapper.map(userDto, User.class);
        user.setId(id);

        // Handle followers update
        if (userDto.getFollowers() != null && !userDto.getFollowers().isEmpty()) {
            Set<User> followers = userDto.getFollowers().stream()
                    .map(summary -> userRepository.findById(summary.getId())
                            .orElseThrow(() -> new RuntimeException("Follower not found with ID: " + summary.getId())))
                    .collect(Collectors.toSet());
            user.setFollowers(followers);
        } else {
            // If no followers are provided, retain the existing ones
            user.setFollowers(existingUser.getFollowers());
        }

        // Handle following update
        if (userDto.getFollowing() != null && !userDto.getFollowing().isEmpty()) {
            Set<User> following = userDto.getFollowing().stream()
                    .map(summary -> userRepository.findById(summary.getId())
                            .orElseThrow(() -> new RuntimeException("Following user not found with ID: " + summary.getId())))
                    .collect(Collectors.toSet());
            user.setFollowing(following);
        } else {
            // If no following users are provided, retain the existing ones
            user.setFollowing(existingUser.getFollowing());
        }

        // Update feedbacks
        if (userDto.getFeedbacks() != null) {
            existingUser.getFeedbacks().clear();
            Set<Feedback> updatedFeedbacks = userDto.getFeedbacks().stream()
                    .map(dto -> {
                        Feedback feedback = mapper.map(dto, Feedback.class);
                        feedback.setUser(existingUser); // maintain relationship
                        return feedback;
                    }).collect(Collectors.toSet());
            existingUser.getFeedbacks().addAll(updatedFeedbacks);
        }

        // Update skillPosts
        if (userDto.getSkillPosts() != null) {
            existingUser.getSkillPosts().clear();
            Set<SkillPosts> updatedPosts = userDto.getSkillPosts().stream()
                    .map(dto -> {
                        SkillPosts post = dto.toEntity(mapper);
                        post.setUser(existingUser);
                        return post;
                    }).collect(Collectors.toSet());
            existingUser.getSkillPosts().addAll(updatedPosts);
        }


        // Save the updated user entity
        User savedUser = userRepository.save(user);

        // Use custom toDto() to ensure correct follower/following mapping
        return savedUser.toDto(mapper);
    }

    // Delete User by ID
    @Override
    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
        return true;
    }

    // Get User by Username
    @Override
    public UserDto getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return mapper.map(user.get(), UserDto.class);
        } else {
            throw new NotFoundException("User not found with username: " + username);
        }
    }

    // Get User by Email
    @Override
    public UserDto getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return mapper.map(user.get(), UserDto.class);
        } else {
            throw new NotFoundException("User not found with email: " + email);
        }
    }
}
