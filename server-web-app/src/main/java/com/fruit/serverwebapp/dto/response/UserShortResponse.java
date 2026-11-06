package com.fruit.serverwebapp.dto.response;

import com.fruit.serverwebapp.model.Role;

/**
 * DTO for {@link com.fruit.serverwebapp.model.User}
 */
public record UserShortResponse(
        Long id,
        String login,
        Role role) { }