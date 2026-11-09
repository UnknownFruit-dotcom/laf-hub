package com.fruit.serverwebapp.controller;

import com.fruit.serverwebapp.dto.request.OrganizationRequest;
import com.fruit.serverwebapp.dto.response.ItemResponse;
import com.fruit.serverwebapp.dto.response.OrganizationCreatedResponse;
import com.fruit.serverwebapp.dto.response.OrganizationResponse;
import com.fruit.serverwebapp.dto.response.ParticipationResponse;
import com.fruit.serverwebapp.service.OrganizationService;
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
    public ResponseEntity<List<OrganizationResponse>> getAllOrgs() {
        return ResponseEntity.ok(organizationService.getAllOrgs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponse> getOrg(@PathVariable Long id) {
        return ResponseEntity.ok(organizationService.getOrgById(id));
    }

    @PostMapping()
    public ResponseEntity<OrganizationCreatedResponse> createOrg(@RequestBody OrganizationRequest request) {
        return ResponseEntity.ok(organizationService.createOrg(request));
    }

    @PostMapping("/{orgId}/join")
    public ResponseEntity<ParticipationResponse> joinOrg(
            @PathVariable Long orgId,
            @RequestParam Long userId,
            @RequestParam String passcode) {
        return ResponseEntity.ok(organizationService.joinOrg(orgId, userId, passcode));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrg(@PathVariable Long id) {
        organizationService.deleteOrg(id);
        return ResponseEntity.noContent().build();
    }
}
