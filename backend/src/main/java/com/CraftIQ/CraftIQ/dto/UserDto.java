package com.CraftIQ.CraftIQ.dto;

import com.CraftIQ.CraftIQ.entity.User;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

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


    public User toEntity(ModelMapper mapper) {
        return mapper.map(this, User.class);
    }
}

