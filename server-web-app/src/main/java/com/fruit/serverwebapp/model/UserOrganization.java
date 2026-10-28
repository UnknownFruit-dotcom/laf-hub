package com.fruit.serverwebapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "user_organizations",
    uniqueConstraints = @UniqueConstraint(
            name = "uk_user_organization",
            columnNames = {"user_id", "organization_id"}
    )
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrganization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @Column(name = "joined_at", nullable = false)
    private LocalDateTime joinedAt;
}
