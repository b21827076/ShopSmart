package com.ariamath.shopsmart.repository;

import com.ariamath.shopsmart.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
    @Query(value = "SELECT c FROM Purchase c WHERE c.user.id = :userId")
    List<Purchase> getPurchasesById(Long userId);
}
