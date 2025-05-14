package com.CraftIQ.CraftIQ.dto;

import com.CraftIQ.CraftIQ.entity.User;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String bio;
    private String profilePicture;
    private String interests;


    public User toEntity(ModelMapper mapper) {
        return mapper.map(this, User.class);
    }
}

