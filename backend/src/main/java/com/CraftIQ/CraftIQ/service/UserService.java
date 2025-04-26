package com.CraftIQ.CraftIQ.service;

import com.CraftIQ.CraftIQ.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    boolean deleteUser(Long id);
    UserDto getUserByUsername(String username);
    UserDto getUserByEmail(String email);
}

