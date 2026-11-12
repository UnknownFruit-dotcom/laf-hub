package com.fruit.serverwebapp.dto.request;

import com.fruit.serverwebapp.model.ItemStatus;
import jakarta.validation.constraints.NotNull;

public record ChangeItemStatusRequest(
        @NotNull ItemStatus newStatus) {
}
