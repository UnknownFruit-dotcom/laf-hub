package com.fruit.serverwebapp.repository;

import com.fruit.serverwebapp.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLogin(@NotNull @NotEmpty String login);

    boolean existsByLoginAndIdNot(String login, Long id);

    @Query("""
    SELECT u FROM User u 
    WHERE (:isActive IS NULL OR u.isActive = :isActive)
    """)
    List<User> findFiltered(@Param("isActive") Boolean isActive);
}
