package com.fruit.serverwebapp.repository;

import com.fruit.serverwebapp.model.Organization;
import com.fruit.serverwebapp.model.User;
import com.fruit.serverwebapp.model.UserOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrganizationRepository extends JpaRepository<UserOrganization, Long> {
    boolean existsByUserAndOrganization(User user, Organization organization);
}
