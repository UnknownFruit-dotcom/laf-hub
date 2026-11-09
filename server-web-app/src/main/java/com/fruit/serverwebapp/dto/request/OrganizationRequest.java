package com.fruit.serverwebapp.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link com.fruit.serverwebapp.model.Organization}
 */
public record OrganizationRequest(
        @NotNull @NotEmpty String title,
        String description,
        @NotNull Long createdById) {
}