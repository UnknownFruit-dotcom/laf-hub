package com.fruit.serverwebapp.service;

import com.fruit.serverwebapp.dto.request.UserCreationRequest;
import com.fruit.serverwebapp.dto.request.UserUpdateRequest;
import com.fruit.serverwebapp.dto.response.UserResponse;
import com.fruit.serverwebapp.exception.AlreadyExistsException;
import com.fruit.serverwebapp.exception.ResourceNotFoundException;
import com.fruit.serverwebapp.mapper.UserMapper;
import com.fruit.serverwebapp.model.Role;
import com.fruit.serverwebapp.model.User;
import com.fruit.serverwebapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse createUser(UserCreationRequest request) {
        User formedUser = formUser(request);

        User saved = userRepository.save(formedUser);
        return userMapper.toResponse(saved);
    }

    @Transactional
    public UserResponse createAdmin(UserCreationRequest request) {
        User formedUser = formUser(request);

        formedUser.setRole(Role.ADMIN);
        User saved = userRepository.save(formedUser);
        return userMapper.toResponse(saved);
    }

    private User formUser(UserCreationRequest request) {
        if (userRepository.existsByLogin(request.login())) {
            throw new AlreadyExistsException("User with login '" + request.login() + "' already exists");
        }

        User user = userMapper.toEntity(request);
        user.setCreatedAt(LocalDateTime.now());

        String hashedPassword = passwordEncoder.encode(request.password());
        user.setPasswordHash(hashedPassword);
        return user;
    }

    public List<UserResponse> getUsers(Boolean isActive) {
        return userMapper.toResponseList(userRepository.findFiltered(isActive));
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));
        return userMapper.toResponse(user);
    }

    @Transactional
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));

        if (request.login() != null && !request.login().isBlank()) {
            if (userRepository.existsByLoginAndIdNot(request.login(), id)) {
                throw new AlreadyExistsException(
                        "User with login \"" + request.login() + "\" already exists");
            }
        }

        userMapper.updateUserFromDto(request, user);
        return userMapper.toResponse(userRepository.save(user));
    }
}
