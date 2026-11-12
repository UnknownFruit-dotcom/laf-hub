package com.fruit.serverwebapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link com.fruit.serverwebapp.model.Organization}
 */
public record OrganizationRequest(
        @NotBlank String title,
        String description,
        @NotNull Long createdById) {
}