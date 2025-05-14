package com.CraftIQ.CraftIQ.service.Impl;

import com.CraftIQ.CraftIQ.dto.LearningPlansDto;
import com.CraftIQ.CraftIQ.entity.LearningPlans;
import com.CraftIQ.CraftIQ.entity.User;
import com.CraftIQ.CraftIQ.exception.NotFoundException;
import com.CraftIQ.CraftIQ.repository.LearningPlansRepository;
import com.CraftIQ.CraftIQ.repository.UserRepository;
import com.CraftIQ.CraftIQ.service.LearningPlansService;
import com.CraftIQ.CraftIQ.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LearningPlansServiceImpl implements LearningPlansService {

    private final LearningPlansRepository learningPlansRepository;
    public final UserRepository userRepository;
    private final ModelMapper mapper;

    // Create Learning Plan
    @Override
    public LearningPlansDto postLearningPlan(LearningPlansDto learningPlansDto) {
        // Convert DTO to Entity
        LearningPlans learningPlan = learningPlansDto.toEntity(mapper);

        // Fetch and set the user if userId is provided
        if (learningPlansDto.getUserId() != null) {
            User user = userRepository.findById(learningPlansDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + learningPlansDto.getUserId()));
            learningPlan.setUser(user);
        }

        // Save Entity
        LearningPlans savedLearningPlan = learningPlansRepository.save(learningPlan);

        // Convert back to DTO and return
        return savedLearningPlan.toDto(mapper);
    }

    // Get all Learning Plans
    @Override
    public List<LearningPlansDto> getAllLearningPlans() {
        List<LearningPlans> learningPlans = learningPlansRepository.findAll();
        if (learningPlans.isEmpty()) {
            return new ArrayList<>();
        } else {
            return learningPlans.stream().map(plan -> plan.toDto(mapper)).toList();
        }
    }

    // Get Learning Plan by ID
    @Override
    public LearningPlansDto getLearningPlanById(Long id) {
        Optional<LearningPlans> learningPlan = learningPlansRepository.findById(id);
        if (learningPlan.isPresent()) {
            return learningPlan.get().toDto(mapper);
        } else {
            throw new NotFoundException("Learning Plan not found with ID: " + id);
        }
    }

    // Update Learning Plan by ID
    @Override
    public LearningPlansDto updateLearningPlan(Long id, LearningPlansDto learningPlansDto) {
        LearningPlans learningPlan = learningPlansDto.toEntity(mapper);
        learningPlan.setId(id);
        LearningPlans savedLearningPlan = learningPlansRepository.save(learningPlan);
        return savedLearningPlan.toDto(mapper);
    }

    // Delete Learning Plan by ID
    @Override
    public Boolean deleteLearningPlan(Long id) {
        if (!learningPlansRepository.existsById(id)) {
            throw new NotFoundException("Learning Plan not found with ID: " + id);
        }
        learningPlansRepository.deleteById(id);
        return true;
    }
}

