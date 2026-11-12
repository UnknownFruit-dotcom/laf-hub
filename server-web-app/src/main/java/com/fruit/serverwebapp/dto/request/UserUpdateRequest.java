package com.fruit.serverwebapp.dto.request;

public record UserUpdateRequest(
        String login,
        Boolean isActive) {
}
