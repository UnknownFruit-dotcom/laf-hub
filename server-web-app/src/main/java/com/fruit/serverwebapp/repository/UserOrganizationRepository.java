package com.fruit.serverwebapp.repository;

import com.fruit.serverwebapp.model.UserOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrganizationRepository extends JpaRepository<UserOrganization, Long> {
}
