package com.CraftIQ.CraftIQ.configs;

import com.CraftIQ.CraftIQ.dto.UserDto;
import com.CraftIQ.CraftIQ.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Tell ModelMapper to skip followers, following, feedbacks, and skillPosts in User to UserDto
        modelMapper.typeMap(User.class, UserDto.class).addMappings(mapper -> {
            mapper.skip(UserDto::setFollowers);
            mapper.skip(UserDto::setFollowing);
            mapper.skip(UserDto::setFeedbacks);
            mapper.skip(UserDto::setSkillPosts);
        });

        return modelMapper;
    }



}