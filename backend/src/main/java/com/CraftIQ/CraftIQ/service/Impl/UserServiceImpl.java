package com.CraftIQ.CraftIQ.service.Impl;

import com.CraftIQ.CraftIQ.dto.UserDto;
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

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    // Create User
    @Override
    public UserDto createUser(UserDto userDto) {
        // Convert DTO to Entity
        User user = mapper.map(userDto, User.class);

        // Save Entity
        User savedUser = userRepository.save(user);

        // Convert back to DTO and return
        return mapper.map(savedUser, UserDto.class);
    }

    // Get all Users
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return new ArrayList<>();
        } else {
            return users.stream().map(user -> mapper.map(user, UserDto.class)).toList();
        }
    }

    // Get User by ID
    @Override
    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return mapper.map(user.get(), UserDto.class);
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
        User user = mapper.map(userDto, User.class);
        user.setId(id);
        User savedUser = userRepository.save(user);
        return mapper.map(savedUser, UserDto.class);
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
