package com.fruit.serverwebapp.controller;

import com.fruit.serverwebapp.dto.request.OrganizationRequest;
import com.fruit.serverwebapp.dto.request.OrganizationUpdateRequest;

import com.fruit.serverwebapp.dto.response.OrganizationCreatedResponse;
import com.fruit.serverwebapp.dto.response.OrganizationResponse;
import com.fruit.serverwebapp.dto.response.ParticipationResponse;
import com.fruit.serverwebapp.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/organizations")
public class OrganizationController {
    private final OrganizationService organizationService;

    @GetMapping()
    public ResponseEntity<List<OrganizationResponse>> getOrgs(@RequestParam(required = false) Boolean isActive) {
        return ResponseEntity.ok(organizationService.getOrgs(isActive));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponse> getOrg(@PathVariable Long id) {
        return ResponseEntity.ok(organizationService.getOrgById(id));
    }

    @PostMapping()
    public ResponseEntity<OrganizationCreatedResponse> createOrg(@Valid @RequestBody OrganizationRequest request) {
        return ResponseEntity.ok(organizationService.createOrg(request));
    }

    @PostMapping("/{orgId}/join")
    public ResponseEntity<ParticipationResponse> joinOrg(
            @PathVariable Long orgId,
            @RequestParam Long userId,
            @RequestParam String passcode) {
        return ResponseEntity.ok(organizationService.joinOrg(orgId, userId, passcode));
    }

    @PostMapping("/{orgId}/leave")
    public ResponseEntity<Void> leaveOrg(
            @PathVariable Long orgId,
            @RequestParam Long userId) {
        organizationService.leaveOrg(orgId, userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrganizationResponse> updateOrg(
            @PathVariable Long id,
            @Valid @RequestBody OrganizationUpdateRequest request) {
        return ResponseEntity.ok(organizationService.updateOrg(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrg(@PathVariable Long id) {
        organizationService.deleteOrg(id);
        return ResponseEntity.noContent().build();
    }
}
