package com.fruit.serverwebapp.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link com.fruit.serverwebapp.model.User}
 */
public record UserCreationRequest(
        @NotNull @NotEmpty String login,
        @NotNull @NotEmpty String password) { }