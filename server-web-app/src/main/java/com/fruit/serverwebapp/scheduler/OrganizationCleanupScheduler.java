package com.fruit.serverwebapp.scheduler;

import com.fruit.serverwebapp.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrganizationCleanupScheduler {
    private final OrganizationService organizationService;

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteInactiveOrganizations() {
        try {
            int removed = organizationService.deleteExpiredDeactivatedOrganizations();

            if (removed != 0) {
                log.info("Permanently deleted {} deactivated organizations", removed);
            }
        } catch (Exception e) {
            log.error("Organization cleanup job failed", e);
        }
    }
}
