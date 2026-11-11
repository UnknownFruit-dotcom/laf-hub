package com.fruit.serverwebapp.repository;

import com.fruit.serverwebapp.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @Query("""
    SELECT o FROM Organization o
    WHERE (:isActive IS NULL OR o.isActive = :isActive)
    """)
    List<Organization> findFiltered(@Param("isActive") Boolean isActive);
}
