package com.fruit.serverwebapp.dto.request;

public record OrganizationUpdateRequest(
        String title,
        String description,
        Boolean isActive) {
}
