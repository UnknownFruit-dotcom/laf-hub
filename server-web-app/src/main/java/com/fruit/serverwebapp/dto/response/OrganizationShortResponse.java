package com.fruit.serverwebapp.dto.response;

import java.io.Serializable;

/**
 * DTO for {@link com.fruit.serverwebapp.model.Organization}
 */
public record OrganizationShortResponse(
        Long id,
        String title) implements Serializable {
}