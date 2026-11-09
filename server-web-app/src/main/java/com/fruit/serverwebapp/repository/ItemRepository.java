package com.fruit.serverwebapp.repository;

import com.fruit.serverwebapp.model.Item;
import com.fruit.serverwebapp.model.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("""
    SELECT i FROM Item i
    LEFT JOIN FETCH i.foundBy
    LEFT JOIN FETCH i.organization
    WHERE (:status IS NULL OR i.status = :status)
        AND (:organizationId IS NULL OR i.organization.id = :organizationId)
        AND (:foundById IS NULL OR i.foundBy.id = :foundById)
    """)
    List<Item> findFiltered(
            @Param("status")ItemStatus status,
            @Param("organizationId")Long organizationId,
            @Param("foundById")Long foundById);
}
