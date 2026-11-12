package com.fruit.serverwebapp.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for {@link com.fruit.serverwebapp.model.User}
 */
public record UserCreationRequest(
        @NotBlank String login,
        @NotBlank String password) { }