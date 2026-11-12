package com.fruit.serverwebapp.controller;

import com.fruit.serverwebapp.dto.request.ChangeItemStatusRequest;
import com.fruit.serverwebapp.dto.request.ItemRequest;
import com.fruit.serverwebapp.dto.response.ItemResponse;
import com.fruit.serverwebapp.model.ItemStatus;
import com.fruit.serverwebapp.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getItem(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @GetMapping()
    public ResponseEntity<List<ItemResponse>> getItems(
            @RequestParam(required = false) ItemStatus status,
            @RequestParam(required = false) Long organizationId,
            @RequestParam(name = "foundBy", required = false) Long foundById) {
        return ResponseEntity.ok(itemService.getItems(status, organizationId, foundById));
    }

    @PostMapping
    public ResponseEntity<ItemResponse> createItem(@Valid @RequestBody ItemRequest request) {
        return ResponseEntity.ok(itemService.createItem(request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ItemResponse> changeItemStatus(@PathVariable Long id, @Valid @RequestBody ChangeItemStatusRequest request) {
        return ResponseEntity.ok(itemService.changeItemStatus(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemResponse> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
