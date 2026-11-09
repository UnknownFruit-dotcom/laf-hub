package com.fruit.serverwebapp.dto.request;

import com.fruit.serverwebapp.model.ItemStatus;

public record ChangeItemStatusRequest(
        ItemStatus newStatus) {
}
