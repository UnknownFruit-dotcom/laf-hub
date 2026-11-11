package com.fruit.serverwebapp.dto.response;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.fruit.serverwebapp.model.Organization}
 */
public record OrganizationCreatedResponse(
        Long id,
        String title,
        String description,
        Boolean isActive,
        String verificationCode,
        UserShortResponse createdBy,
        LocalDateTime createdAt) {
}