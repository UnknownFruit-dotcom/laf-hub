package com.fruit.serverwebapp.dto.response;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.fruit.serverwebapp.model.Organization}
 */
public record OrganizationResponse(
        Long id,
        String title,
        String description,
        Boolean isActive,
        LocalDateTime lastActivityAt,
        UserShortResponse createdBy,
        LocalDateTime createdAt) {
}