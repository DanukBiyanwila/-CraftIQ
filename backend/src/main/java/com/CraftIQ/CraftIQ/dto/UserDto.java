package com.CraftIQ.CraftIQ.dto;

import com.CraftIQ.CraftIQ.entity.User;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String passwordHash;
    private String bio;
    private String profilePicture;
    private String interests;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;

    public User toEntity(ModelMapper mapper) {
        return mapper.map(this, User.class);
    }
}

