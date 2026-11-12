package com.fruit.serverwebapp.dto.request;

import com.fruit.serverwebapp.model.ItemStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.fruit.serverwebapp.model.Item}
 */
public record ItemRequest(
        @NotBlank String title,
        @NotBlank String description,
        @NotNull @PastOrPresent LocalDateTime foundAt,
        @NotNull Long foundById,
        @NotNull Long organizationId,
        @NotNull ItemStatus status) {
}