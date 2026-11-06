package com.fruit.serverwebapp.dto.response;

import com.fruit.serverwebapp.model.ItemStatus;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.fruit.serverwebapp.model.Item}
 */
public record ItemResponse(
        Long id,
        String title,
        String description,
        LocalDateTime foundAt,
        LocalDateTime createdAt,

        UserShortResponse foundBy,
        OrganizationShortResponse organization,

        ItemStatus status) { }