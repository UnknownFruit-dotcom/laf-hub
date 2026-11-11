package com.fruit.serverwebapp.dto.response;

import com.fruit.serverwebapp.model.Role;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.fruit.serverwebapp.model.User}
 */
public record UserResponse(
        Long id,
        String login,
        LocalDateTime createdAt,
        Role role) { }